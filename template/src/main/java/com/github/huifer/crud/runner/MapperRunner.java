package com.github.huifer.crud.runner;

import com.github.huifer.crud.annotation.CacheKey;
import com.github.huifer.crud.interfaces.A;
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
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MapperRunner implements CommandLineRunner, Ordered {

  public static Map<Class, A> om = new HashMap<>();
  public static Map<Class, MapperAndCacheInfo> mapperAndCacheInfoMap = new HashMap<>();
  @Autowired
  private ApplicationContext context;
  @Autowired
  private SqlSession sqlSession;

  public static A getA(Class a) {
    return om.get(a);
  }

  public static MapperAndCacheInfo getMapperAndCacheInfo(Class clazz) {
    return mapperAndCacheInfoMap.get(clazz);
  }

  @Override
  public void run(String... args) throws Exception {
    this.hh();
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }

  public void hh() {
    Configuration configuration = sqlSession.getConfiguration();
    MapperRegistry mapperRegistry = configuration.getMapperRegistry();
    Collection<Class<?>> mappers = mapperRegistry.getMappers();

    for (Class<?> mapper : mappers) {
      if (mapper.isInterface()) {

        CacheKey annotation = mapper.getAnnotation(CacheKey.class);

        Type[] genericInterfaces = mapper.getGenericInterfaces();

        if (genericInterfaces.length > 0) {

          for (Type genericInterface : genericInterfaces) {
            ParameterizedType pt = (ParameterizedType) genericInterface;
            Class rawType = (Class) pt.getRawType();
            if (rawType.equals(A.class)) {
              Type[] r = pt.getActualTypeArguments();
              if (r.length == 2) {
                MapperAndCacheInfo mapperAndCacheInfo = new MapperAndCacheInfo();

                // 获取接口泛型
                Class id = (Class) r[0];
                Class type = (Class) r[1];

                Object mapper1 = sqlSession.getMapper(mapper);
                om.put(type, (A) mapper1);
                mapperAndCacheInfo.setA((A) mapper1);
                mapperAndCacheInfo.setClazz(type);
                mapperAndCacheInfo.setMapperClazz(mapper);

                if (annotation != null) {
                  String key = annotation.key();

                  Class<?> typeC = annotation.type();
                  if (type.equals(typeC)) {
                    if (!StringUtils.isEmpty(key)) {

                      mapperAndCacheInfo.setKey(key);


                    }
                    else {
                      throw new RuntimeException("cache key not null , class+ " + mapper);
                    }
                  }
                  else {
                    throw new RuntimeException("cache type not matching，class = " + mapper);
                  }
                }
                putData(mapperAndCacheInfo);
              }

            }
          }

        }
      }
    }
  }

  private void putData(MapperAndCacheInfo mapperAndCacheInfo) {
    MapperAndCacheInfo cache = mapperAndCacheInfoMap
        .get(mapperAndCacheInfo.getClazz());

    if (cache != null) {
      throw new RuntimeException("Type already exists" + mapperAndCacheInfo.getMapperClazz());
    }

    mapperAndCacheInfoMap.forEach(
        (k, v) -> {
          String key = v.getKey();
          if (key.equals(mapperAndCacheInfo.getKey())) {
            throw new RuntimeException(
                "The same cache key value exists " + v.getMapperClazz() + "\t" + mapperAndCacheInfo
                    .getMapperClazz());
          }
        }

    );

    mapperAndCacheInfoMap.put(mapperAndCacheInfo.getClazz(), mapperAndCacheInfo);

  }


}