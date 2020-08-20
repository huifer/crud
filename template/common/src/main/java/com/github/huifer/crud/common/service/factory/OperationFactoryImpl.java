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

package com.github.huifer.crud.common.service.factory;

import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.intefaces.CrudTemplate;
import com.github.huifer.crud.common.intefaces.operation.DbOperation;
import com.github.huifer.crud.common.intefaces.operation.RedisOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("operationFactoryImpl")
public class OperationFactoryImpl implements OperationFactory {


  @Autowired
  @Qualifier("crudHashTemplateForRedis")
  private RedisOperation redisOperation;

  @Autowired
  private ApplicationContext context;


  @Override
  public OperationCollection factory(DaoType daoType) {
    OperationCollection operationCollection = new OperationCollection();

    operationCollection.setRedisOperation(redisOperation);

    Map<String, DbOperation> beansOfType = context.getBeansOfType(DbOperation.class);
    CrudTemplate dbOperation = null;
    for (Map.Entry<String, DbOperation> entry : beansOfType.entrySet()) {
      DbOperation v = entry.getValue();
      boolean equals = v.DAO_TYPE().equals(daoType);
      if (equals) {
        dbOperation = (CrudTemplate) v;
        break;
      }
    }

    operationCollection.setDbOperation(dbOperation);

    return operationCollection;
  }
}
