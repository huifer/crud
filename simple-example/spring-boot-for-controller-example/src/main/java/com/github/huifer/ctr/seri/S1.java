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

package com.github.huifer.ctr.seri;

import com.github.huifer.crud.common.model.enums.JsonEnums;
import com.github.huifer.crud.common.serialize.Serialization;
import org.springframework.stereotype.Service;

@Service("sss1")
public class S1 implements Serialization {

  @Override
  public JsonEnums jsonType() {
    return JsonEnums.GSON;
  }

  @Override
  public String serialize(Object object) {
    return null;
  }

  @Override
  public Object deserialize(String json, Class<?> type) {
    return null;
  }
}