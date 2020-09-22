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

import static com.github.huifer.crud.common.utils.Constant.SERIALIZATION_FACTORY_BEAN_NAME;
import com.github.huifer.crud.common.serialize.Serialization;
import com.github.huifer.crud.common.serialize.SerializationCall;
import com.github.huifer.crud.common.serialize.SerializationFactory;
import com.github.huifer.crud.common.utils.Constant;
import com.github.huifer.crud.common.utils.EnableAttrManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(Constant.SERIALIZATION_CALL_IMPL)
public class SerializationCallImpl implements
    SerializationCall {

  @Autowired
  @Qualifier(SERIALIZATION_FACTORY_BEAN_NAME)
  private SerializationFactory serializationFactory;

  @Override
  public String toJson(Object object) {
    Serialization factory = serializationFactory.factory(EnableAttrManager.getJsonEnums());
    return factory.serialize(object);
  }

  @Override
  public Object fromJson(String json, Class<?> clazz) {
    Serialization factory = serializationFactory.factory(EnableAttrManager.getJsonEnums());
    return factory.deserialize(json, clazz);
  }
}
