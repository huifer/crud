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

package com.github.huifer.crud.common.operation;


import static com.github.huifer.crud.common.utils.Constant.COMMON_DB_OPERATION_BEAN_NAME;
import com.github.huifer.crud.common.intefaces.BaseEntity;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.intefaces.operation.DbOperation;
import com.github.huifer.crud.common.proxy.MapperTarget;
import com.github.huifer.crud.common.proxy.MethodUtils;
import com.github.huifer.crud.common.runner.MapperSuperRunner;
import com.github.huifer.crud.common.utils.EnableAttrManager;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(COMMON_DB_OPERATION_BEAN_NAME)
public class CommonDbOperation implements
    DbOperation {

  Class<?> type;

  @Autowired
  private SqlSession sqlSession;

  public Class<?> getMapperClazz() {
    return MapperSuperRunner.mapper(type());
  }

  @Override
  public boolean del(IdInterface interfaces) {
    boolean del = false;
    Class<?> mapperClazz = getMapperClazz();

    Object mapperObj = Proxy.newProxyInstance(mapperClazz.getClassLoader(),
        new Class[]{mapperClazz},
        new MapperTarget(sqlSession.getMapper(mapperClazz))
    );

    Method method = MethodUtils.getMethod(mapperClazz, EnableAttrManager.getDeleteByIdMethodName());
    if (method != null) {

      Object invoke = MethodUtils.invoke(mapperObj, method, interfaces.id());
      if (invoke instanceof Integer) {
        int res = (int) invoke;
        del = res > 0;
      }
    }
    return del;
  }

  @Override
  public <T extends BaseEntity> boolean editor(IdInterface interfaces, T t) {
    this.type = t.getClass();
    boolean editor = false;
    Class<?> mapperClazz = getMapperClazz();

    Object mapperObj = Proxy.newProxyInstance(mapperClazz.getClassLoader(),
        new Class[]{mapperClazz},
        new MapperTarget(sqlSession.getMapper(mapperClazz))
    );

    Method method = MethodUtils.getMethod(mapperClazz, EnableAttrManager.getUpdateByIdMethodName());
    if (method != null) {

      Object invoke = MethodUtils.invoke(mapperObj, method, t);
      if (invoke instanceof Integer) {
        int res = (int) invoke;
        editor = res > 0;
      }
    }
    return editor;
  }

  public <T extends BaseEntity> boolean insert(T o, Class<?> c) {
    this.type = c;
    boolean insert = false;
    Class<?> mapperClazz = getMapperClazz();

    Object mapperObj = Proxy.newProxyInstance(mapperClazz.getClassLoader(),
        new Class[]{mapperClazz},
        new MapperTarget(sqlSession.getMapper(mapperClazz))
    );

    Method method = MethodUtils.getMethod(mapperClazz, EnableAttrManager.getInsertMethodName());
    if (method != null) {

      Object invoke = MethodUtils.invoke(mapperObj, method, o);
      if (invoke instanceof Integer) {
        int res = (int) invoke;
        insert = res > 0;
      }
    }
    return insert;

  }

  public <T extends BaseEntity> T byId(IdInterface idInterface, Class<?> c) {
    this.type = c;

    Class<?> mapperClazz = getMapperClazz();

    Object mapperObj = Proxy.newProxyInstance(mapperClazz.getClassLoader(),
        new Class[]{mapperClazz},
        new MapperTarget(sqlSession.getMapper(mapperClazz))
    );

    Method method = MethodUtils.getMethod(mapperClazz, EnableAttrManager.getSelectByIdMethodName());
    if (method != null) {

      Object invoke = MethodUtils.invoke(mapperObj, method, idInterface.id());
      if (invoke != null) {
        return (T) invoke;
      }
    }

    return null;
  }


  public Class type() {
    return this.type;
  }
}