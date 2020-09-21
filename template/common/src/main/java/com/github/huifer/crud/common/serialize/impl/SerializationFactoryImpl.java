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

package com.github.huifer.crud.common.serialize.impl;


import com.github.huifer.crud.common.model.enums.JsonEnums;
import com.github.huifer.crud.common.serialize.Serialization;
import com.github.huifer.crud.common.serialize.SerializationFactory;
import com.github.huifer.crud.common.utils.Constant;
import com.github.huifer.crud.common.utils.EnableAttrManager;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service("serializationFactoryImpl")
public class SerializationFactoryImpl implements SerializationFactory {

  @Autowired
  ApplicationContext context;

  @Override
  public Serialization factory(JsonEnums jsonEnums) {
    Map<String, Serialization> beansOfType = context.getBeansOfType(Serialization.class);
    AtomicReference<Serialization> result = new AtomicReference<>();

    removeOtherJsonSerialization(jsonEnums, beansOfType);

    if (beansOfType.size() == 1) {
      setSerialization(beansOfType, result);
    } else {
      for (String serializationBeanName : Constant.SERIALIZATION_BEAN_NAMES) {
        beansOfType.remove(serializationBeanName);
      }

      setSerialization(beansOfType, result);
    }

    return result.get();
  }

  private void removeOtherJsonSerialization(JsonEnums jsonEnums,
      Map<String, Serialization> beansOfType) {
    // remove not match class
    Iterator<Entry<String, Serialization>> iterator = beansOfType.entrySet().iterator();

    while (iterator.hasNext()) {
      Entry<String, Serialization> next = iterator.next();

      Serialization value = next.getValue();
      if (!value.jsonType().equals(jsonEnums)) {
        iterator.remove();
      }
    }
  }

  private void setSerialization(Map<String, Serialization> beansOfType,
      AtomicReference<Serialization> result) {
    for (Entry<String, Serialization> entry : beansOfType.entrySet()) {
      Serialization v = entry.getValue();
      if (v.jsonType().equals(EnableAttrManager.getJsonEnums())) {
        result.set(v);
        break;
      }
    }
  }
}
