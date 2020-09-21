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

package com.github.huifer.crud.ctr.service;

import com.github.huifer.crud.common.intefaces.BaseEntity;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.intefaces.id.StrIdInterface;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import org.springframework.stereotype.Service;

@Service("crudFacadeForController")
public class CrudFacadeForController extends CrudFacade {

  public <T extends BaseEntity> T byIdForController(Object id, Class<?> c) {
    redisOperation.setClass(c);

    Object o = redisOperation.byId(new StrIdInterface() {
      @Override
      public String id() {
        return String.valueOf(id);
      }
    });
    if (o != null) {
      return (T) o;
    }
    // select by db
    Object db = dbOperation.byId(new IdInterface() {
      @Override
      public Object id() {
        return id;
      }
    }, c);
    if (db != null) {

      // insert redis
      redisOperation.insert(db, new StrIdInterface() {
        @Override
        public String id() {
          return String.valueOf(id);
        }
      });
      return (T) db;
    }

    return null;
  }

}
