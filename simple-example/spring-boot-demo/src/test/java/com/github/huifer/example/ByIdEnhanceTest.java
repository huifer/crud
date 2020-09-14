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

package com.github.huifer.example;

import com.github.huifer.crud.common.annotation.ByIdEnhance;
import com.github.huifer.crud.common.intefaces.enhance.EnhanceService;
import com.github.huifer.crud.common.proxy.MapperTarget;
import com.github.huifer.example.mapper.TotalModelMapper;
import com.github.huifer.example.model.TotalEnhance;
import com.github.huifer.example.model.TotalModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ByIdEnhanceTest {

  @Autowired
  private TotalModelMapper totalModelMapper;


  @Autowired
  private SqlSession sqlSession;

  @Autowired
  @Qualifier("enhanceServiceImpl")
  private EnhanceService enhanceService;

  @Test
  void t2()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    TotalModel totalModel = totalModelMapper.findById(1);
    TotalEnhance totalEnhance = new TotalEnhance();
    BeanUtils.copyProperties(totalModel, totalEnhance);
    Object enhance = enhanceService.enhance(totalEnhance);
    System.out.println();
  }

  @Test
  public void tt() throws Exception {
    TotalModel totalModel = totalModelMapper.findById(1);

    // bean copy
    TotalEnhance totalEnhance = new TotalEnhance();
    BeanUtils.copyProperties(totalModel, totalEnhance);

    // 找注解

    Class<?> enhanceClass = totalEnhance.getClass();

    for (Field declaredField : enhanceClass.getDeclaredFields()) {
      declaredField.setAccessible(true);
      ByIdEnhance annotation = declaredField.getAnnotation(ByIdEnhance.class);
      if (annotation != null) {
        String foreignKey = annotation.foreignKey();
        Class<?> mapper = annotation.mapper();
        String queryMethod = annotation.queryMethod();

        Object foreignKeyValue = getField(totalEnhance, enhanceClass, foreignKey);
        Object q = q(mapper, queryMethod, annotation.idType(), foreignKeyValue);

        // 需要处理的字段
        Class<?> type = declaredField.getType();
        if (q.getClass().equals(type)) {
          declaredField.set(totalEnhance, q);
        }
      }
    }
    System.out.println();
  }

  private Object getField(Object t, Class<?> entityClass, String field)
      throws IllegalAccessException {
    List<Field> allFields = getAllFields(entityClass);
    Object result = null;
    for (Field allField : allFields) {
      allField.setAccessible(true);
      if (allField.getName().equals(field)) {
        result = allField.get(t);
      }
    }
    return result;
  }

  public List<Field> getAllFields(Class<?> input) {
    List<Field> fieldList = new ArrayList<>();
    while (input != null && !input.getName().toLowerCase().equals("java.lang.object")) {
      fieldList.addAll(Arrays.asList(input.getDeclaredFields()));
      input = input.getSuperclass(); //得到父类,然后赋给自己
    }
    return fieldList;
  }

  private Object q(Class<?> mapper, String method, Class<?> idType, Object id) throws Exception {

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
