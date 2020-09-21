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

package com.github.huifer.crud.ctr.validated;

import com.github.huifer.crud.ctr.entity.OpEnums;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ValidatedScanService {

  @Autowired
  private ApplicationContext context;


  public void invoke(Object o, Class<?> clazz, OpEnums opEnums) {

    Map<String, ValidatedInterface> beansOfType = context.getBeansOfType(ValidatedInterface.class);
    for (Entry<String, ValidatedInterface> entry : beansOfType.entrySet()) {
      ValidatedInterface<Object> v = entry.getValue();
      if (v.entityClass().equals(clazz)) {
        if (opEnums == OpEnums.ADD) {
          v.validateAdd(o);
        } else if (opEnums == OpEnums.BY_ID) {
          v.validateById(o);
        } else if (opEnums == OpEnums.DELETE) {
          v.validateDelete(o);
        } else if (opEnums == OpEnums.EDITOR) {
          v.validateEditor(o);
        }

      }
    }
  }

}
