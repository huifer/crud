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

import com.github.huifer.crud.common.annotation.entity.DiffAnnotationEntity;
import com.github.huifer.crud.common.intefaces.diff.IDiffInterface;
import com.github.huifer.crud.common.model.diff.DiffInfoEntity;
import com.github.huifer.crud.common.proxy.MapperTarget;
import com.github.huifer.crud.common.runner.DiffRunner;
import com.github.huifer.crud.common.utils.EnableAttrManager;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class IDiffInterfaceImpl<T> implements IDiffInterface<T> {

  private static final String OLD_PLACEHOLDER = "old";
  private static final String NEW_PLACEHOLDER = "new";
  @Autowired
  private SqlSession sqlSession;


  @Override
  public List<DiffInfoEntity> diff(T source, T target, String logTxId) {

    Class<?> sourceClass = source.getClass();
    List<DiffInfoEntity> res = new ArrayList<>();
    for (Field declaredField : sourceClass.getDeclaredFields()) {
      declaredField.setAccessible(true);
      // filed name
      String fieldName = declaredField.getName();

      String oldValue = getTargetValue(source, fieldName);
      String newValue = getTargetValue(target, fieldName);

      // get diffAnnotation entity
      DiffAnnotationEntity fromFiled = getFromFiled(source, fieldName);
      if (fromFiled != null) {

        // filed cnName
        String nameCn = fromFiled.getName();

        // foreign type filed name
        String outField = fromFiled.getOutField();
        // foreign type class
        Class<?> outJoin = fromFiled.getOutJoin();
        // mapper class
        Class<?> mapper = fromFiled.getMapper();

        if (StringUtils.isEmpty(outField) &&
            outJoin.equals(Object.class) &&
            mapper.equals(Object.class)
        ) {
          if (oldValue.equals(newValue)) {

            String changeLog = changeData(oldValue, newValue, fromFiled.getMsg());
            DiffInfoEntity diffInfoEntity = genDiffInfoEntity(logTxId, nameCn, oldValue, newValue,
                changeLog);
            res.add(diffInfoEntity);

          }
        }
        else {
          String ov = mapper(mapper, oldValue, outField);
          String nv = mapper(mapper, newValue, outField);
          if (ov.equals(nv)) {

            String changeLog = changeData(ov, nv, fromFiled.getMsg());
            DiffInfoEntity diffInfoEntity = genDiffInfoEntity(logTxId, nameCn, ov, nv, changeLog);
            res.add(diffInfoEntity);
          }
        }
      }
    }
    return res;


  }

  private DiffInfoEntity genDiffInfoEntity(String logTxId, String nameCn, String ov, String nv,
      String changeLog) {
    DiffInfoEntity diffInfoEntity = new DiffInfoEntity();
    diffInfoEntity.setField(nameCn);
    diffInfoEntity.setMsg(changeLog);
    diffInfoEntity.setNv(nv);
    diffInfoEntity.setOv(ov);
    diffInfoEntity.setTxId(logTxId);
    return diffInfoEntity;
  }


  private String mapper(Class<?> mapper, Serializable serializable, String filed) {
    try {
      Class<?> aClass = Class.forName(mapper.getName());
      Object mapperObj = Proxy.newProxyInstance(aClass.getClassLoader(),
          new Class[]{mapper},
          new MapperTarget(sqlSession.getMapper(mapper))
      );
      Method selectById = mapperObj.getClass()
          .getMethod(EnableAttrManager.getByIdMethod(), Serializable.class);
      Object invoke = selectById.invoke(mapperObj, serializable);
      return getValue(invoke, filed, "");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * get change message
   */
  private String changeData(String oldValue, String newValue, String msg) {
    return msg.replace(OLD_PLACEHOLDER, oldValue).replace(NEW_PLACEHOLDER, newValue);
  }

  private String getTargetValue(T t, String field) {
    String result = "";
    result = getValue(t, field, result);

    return result;
  }

  private String getValue(Object t, String field, String result) {
    Class<?> aClass = t.getClass();
    for (Field declaredField : aClass.getDeclaredFields()) {

      declaredField.setAccessible(true);

      String fieldName = declaredField.getName();
      if (field.equals(fieldName)) {
        try {
          Object o = declaredField.get(t);
          result = String.valueOf(o);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  /**
   * gets the annotation entity object according to the type
   * <p>
   * key:filed,value: DiffAnnotationEntity
   *
   * @see DiffAnnotationEntity
   */
  private Map<String, DiffAnnotationEntity> getFromClazz(T t) {
    return DiffRunner.get(t.getClass());
  }

  private DiffAnnotationEntity getFromFiled(T t, String field) {
    return getFromClazz(t).get(field);
  }


}