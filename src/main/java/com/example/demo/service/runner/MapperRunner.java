package com.example.demo.service.runner;

import com.example.demo.mapper.A;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class MapperRunner {

  public static Map<Class, A> om = new HashMap<>();
  public static Map<A, Class> MO = new HashMap<>();
  @Autowired
  private ApplicationContext context;
  @Autowired
  private SqlSession sqlSession;

  public static A getA(Class a) {

    return om.get(a);
  }

  @PostConstruct
  public void hh() {
    Configuration configuration = sqlSession.getConfiguration();
    MapperRegistry mapperRegistry = configuration.getMapperRegistry();
    Collection<Class<?>> mappers = mapperRegistry.getMappers();

    for (Class<?> mapper : mappers) {
      if (mapper.isInterface()) {

        Type[] genericInterfaces = mapper.getGenericInterfaces();

        if (genericInterfaces.length > 0) {

          ParameterizedType genericInterface = (ParameterizedType) genericInterfaces[0];
          Type[] r = genericInterface.getActualTypeArguments();

          if (r.length == 2) {
            Class id = (Class) r[0];
            Class type = (Class) r[1];

            Object mapper1 = sqlSession.getMapper(mapper);
            om.put(type, (A) mapper1);
            MO.put((A) mapper1, type);
            System.out.println();
          }
        }
      }
    }
    System.out.println();
  }


}