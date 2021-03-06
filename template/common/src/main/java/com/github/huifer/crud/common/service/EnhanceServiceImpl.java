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

package com.github.huifer.crud.common.service;

import static com.github.huifer.crud.common.utils.Constant.EnhanceService_BEAN_NAME;
import com.github.huifer.crud.common.annotation.ByIdEnhance;
import com.github.huifer.crud.common.annotation.entity.ByIdEnhanceEntity;
import com.github.huifer.crud.common.intefaces.enhance.EnhanceService;
import com.github.huifer.crud.common.proxy.MapperTarget;
import com.github.huifer.crud.common.utils.ClassUtils;
import com.github.huifer.crud.common.utils.Constant;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(Constant.EnhanceService_BEAN_NAME)
public class EnhanceServiceImpl<T> implements EnhanceService<T> {

  private final Map<Class<?>, ByIdEnhance> classByIdEnhanceEntityMap = new ConcurrentHashMap<>(64);

  @Autowired
  private SqlSession sqlSession;

  @Override
  public T enhance(T t)
      throws Exception {
    Class<?> enhanceClass = t.getClass();
    calcData(t, enhanceClass);
    return t;
  }

  private void calcData(T t, Class<?> enhanceClass)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    List<Field> allFields = ClassUtils.getAllFields(enhanceClass);
    Object q = null;
    for (Field field : allFields) {

      Class<?> fieldType = field.getType();

      field.setAccessible(true);

      // cache value
      ByIdEnhance cacheByIdEnhance = classByIdEnhanceEntityMap.get(fieldType);
      if (cacheByIdEnhance != null) {
        q = calc(t, enhanceClass, q, field, cacheByIdEnhance);
      } else {
        ByIdEnhance annotation = field.getAnnotation(ByIdEnhance.class);
        classByIdEnhanceEntityMap.put(fieldType, annotation);
        q = calc(t, enhanceClass, q, field, annotation);
      }
    }
  }


  private Object calc(T t, Class<?> enhanceClass, Object q, Field field, ByIdEnhance annotation)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    if (annotation != null) {
      String foreignKey = annotation.foreignKey();
      Class<?> mapper = annotation.mapper();
      String queryMethod = annotation.queryMethod();

      ByIdEnhanceEntity byIdEnhanceEntity = new ByIdEnhanceEntity(annotation);

      Object foreignKeyValue = ClassUtils.getFieldValue(t, enhanceClass, foreignKey);
      if (foreignKeyValue != null) {
        q = q(mapper, queryMethod, annotation.idType(), foreignKeyValue);
      }
      // Fields to be processed
      Class<?> type = field.getType();
      if (q != null) {
        if (q.getClass().equals(type)) {
          field.set(t, q);
        }
      }
      calcData((T) q, type);
    }
    return q;
  }

  private Object q(Class<?> mapper, String method, Class<?> idType, Object id)
      throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {

    Class<?> aClass = Class.forName(mapper.getName());
    Object mapperObj = Proxy.newProxyInstance(aClass.getClassLoader(),
        new Class[]{mapper},
        new MapperTarget(sqlSession.getMapper(mapper))
    );
    Method selectById = mapperObj.getClass()
        .getMethod(method, idType);
    Object invoke = selectById.invoke(mapperObj, id);
    return invoke;
  }
}
