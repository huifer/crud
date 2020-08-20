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

package com.github.huifer.crud.mybatis.service;


import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.intefaces.BaseEntity;
import com.github.huifer.crud.common.intefaces.CrudTemplate;
import com.github.huifer.crud.common.intefaces.DaoTypeLabel;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.operation.CommonDbOperation;
import org.springframework.stereotype.Service;

@Service("crudTemplateForMybatis")
public class CrudTemplateForMybatis<T extends BaseEntity, I extends IdInterface>
    extends CommonDbOperation<T, I>
    implements CrudTemplate<T, I>, DaoTypeLabel {

  Class<?> type;

  @Override
  public DaoType DAO_TYPE() {
    return DaoType.MYBATIS;
  }

  @Override
  public Class type() {
    return this.type;
  }

  @Override
  public boolean insert(T t) {
    type = t.getClass();
    return super.insert(t, type);
  }

  @Override
  public T byId(I i, Class<?> c) {
    this.type = c;
    return super.byId(i, c);
  }

  @Override
  public boolean del(I i, Class<?> c) {
    this.type = c;
    return super.del(i, c);
  }

  @Override
  public boolean editor(T t) {
    this.type = t.getClass();
    return super.update(t);
  }


}