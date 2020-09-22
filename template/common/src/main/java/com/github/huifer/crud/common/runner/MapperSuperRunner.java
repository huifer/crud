/*
 *
 * Copyright 2020-2020 HuiFer All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.huifer.crud.common.runner;

import com.github.huifer.crud.common.annotation.CacheKey;
import com.github.huifer.crud.common.utils.EnableAttrManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order
public class MapperSuperRunner implements CommandLineRunner {

  /**
   * key: entity.class value: object
   */
  private static final Map<Class<?>, Obc> mapperMap = new HashMap<>();

  @Autowired
  private ApplicationContext context;

  @Autowired
  private SqlSession sqlSession;

  public static Class<?> mapper(Class<?> type) {
    return mapperMap.get(type).mapperClazz;
  }

  public static String getCacheKey(Class<?> type) {
    return mapperMap.get(type).key;
  }


  @Override
  public void run(String... args) throws Exception {
    this.mybatis();
  }


  public void mybatis() throws Exception {

    Configuration configuration = sqlSession.getConfiguration();
    Collection<MappedStatement> mappedStatements = configuration.getMappedStatements();
    MapperRegistry mapperRegistry = configuration.getMapperRegistry();
    Collection<Class<?>> mappers = mapperRegistry.getMappers();

    for (Object oc : mappedStatements) {
      String key = null;
      Class<?> mapperClazz = null;
      Class<?> returnType = null;
      Class<?> idType = null;
      if (oc instanceof MappedStatement) {
        MappedStatement mappedStatement = (MappedStatement) oc;

        String id = mappedStatement.getId();
        if (id.endsWith(EnableAttrManager.getSelectByIdMethodName())) {

          String replace = id.replace("." + EnableAttrManager.getSelectByIdMethodName(), "");
          Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(replace);
          Object mapper = sqlSession.getMapper(aClass);

          CacheKey annotation = aClass.getAnnotation(CacheKey.class);
          key = annotation.key();
          List<ResultMap> resultMaps = mappedStatement.getResultMaps();
          mapperClazz = aClass;
          returnType = resultMaps.get(0).getType();
          ParameterMap parameterMap = mappedStatement.getParameterMap();
          idType = parameterMap.getType();

        }
      }
      if (returnType != null) {
        mapperMap.put(returnType, new Obc(key, mapperClazz, idType));
      }

    }
    System.out.println();
  }


  private static class Obc {

    /**
     * {@link CacheKey#key()}
     */
    String key;

    /**
     * mapper class
     */
    Class<?> mapperClazz;

    Class<?> idType;

    public Obc(String key, Class<?> mapperClazz, Class<?> idType) {
      this.key = key;
      this.mapperClazz = mapperClazz;
      this.idType = idType;
    }

    public Obc(String key, Class<?> mapperClazz) {
      this.key = key;
      this.mapperClazz = mapperClazz;
    }
  }
}