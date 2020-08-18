package com.github.huifer.crud.mybatis.runner;

import com.github.huifer.crud.common.annotation.CacheKey;
import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.daotype.EnableCrudTemplateThreadLocal;
import com.github.huifer.crud.common.intefaces.A;
import com.github.huifer.crud.common.runner.CrudTemplateRunner;
import com.github.huifer.crud.common.runner.MapperAndCacheInfo;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Order
public class MapperRunner extends CrudTemplateRunner implements CommandLineRunner {

  @Autowired
  private ApplicationContext context;
  @Autowired
  private SqlSession sqlSession;

  public static A getA(Class<?> a) {
    return mapperAndCacheInfoMap.get(a).getA();
  }

  public static MapperAndCacheInfo getMapperAndCacheInfo(Class clazz) {
    return mapperAndCacheInfoMap.get(clazz);
  }

  @Override
  public void run(String... args) throws Exception {
    DaoType daoType = EnableCrudTemplateThreadLocal.getDaoType();
    this.mybatis();
    System.out.println(daoType);
  }


  public void mybatis() {
    Configuration configuration = sqlSession.getConfiguration();
    MapperRegistry mapperRegistry = configuration.getMapperRegistry();
    Collection<Class<?>> mappers = mapperRegistry.getMappers();

    for (Class<?> mapper : mappers) {
      if (mapper.isInterface()) {

        CacheKey annotation = mapper.getAnnotation(CacheKey.class);

        Type[] genericInterfaces = mapper.getGenericInterfaces();

        if (genericInterfaces.length > 0) {

          Class<?> clazz = null;
          String key = null;
          A a = null;
          Class<?> mapperClazz = mapper;
          for (Type genericInterface : genericInterfaces) {
            ParameterizedType pt = (ParameterizedType) genericInterface;
            Class<?> rawType = (Class<?>) pt.getRawType();
            if (rawType.equals(A.class)) {
              Type[] r = pt.getActualTypeArguments();
              if (r.length == 2) {
                // 获取接口泛型
                Class<?> type = (Class<?>) r[1];
                Object mapper1 = sqlSession.getMapper(mapper);
                a = (A) mapper1;
                clazz = type;

                key = cacheKeyInfo(mapper, annotation, key, type);
              }

            }
          }

          setMapperAndCacheInfo(clazz, key, a, mapperClazz);
        }
      }
    }
  }

  /**
   * get {@code CacheKey} attribute
   *
   * @param mapper   mapper class
   * @param cacheKey cacheKey
   * @param key      key
   * @param type     type
   * @return key
   */
  private String cacheKeyInfo(Class<?> mapper, CacheKey cacheKey, String key, Class<?> type) {
    if (cacheKey != null) {
      String annKey = cacheKey.key();
      Class<?> typeC = cacheKey.type();
      if (type.equals(typeC)) {
        if (!StringUtils.isEmpty(annKey)) {

          key = annKey;

        } else {
          throw new RuntimeException("cache key not null , class+ " + mapper);
        }
      } else {
        throw new RuntimeException("cache type not matching，class = " + mapper);
      }
    }
    return key;
  }

  private void setMapperAndCacheInfo(Class<?> clazz, String key, A a, Class<?> mapperClazz) {
    MapperAndCacheInfo mapperAndCacheInfo = new MapperAndCacheInfo();
    mapperAndCacheInfo.setClazz(clazz);
    mapperAndCacheInfo.setKey(key);
    mapperAndCacheInfo.setA(a);
    mapperAndCacheInfo.setMapperClazz(mapperClazz);
    putData(mapperAndCacheInfo);
  }

  private void putData(MapperAndCacheInfo mapperAndCacheInfo) {
    MapperAndCacheInfo cache = mapperAndCacheInfoMap
        .get(mapperAndCacheInfo.getClazz());

    if (cache == null) {

      mapperAndCacheInfoMap.forEach(
          (k, v) -> {
            String key = v.getKey();

            if (!StringUtils.isEmpty(key)) {

              if (key.equals(mapperAndCacheInfo.getKey())) {
                throw new RuntimeException(
                    "The same cache key value exists " + v.getMapperClazz() + "\t"
                        + mapperAndCacheInfo
                        .getMapperClazz());
              }
            }
          }

      );

      mapperAndCacheInfoMap.put(mapperAndCacheInfo.getClazz(), mapperAndCacheInfo);
    }

  }


}