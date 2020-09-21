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

package com.github.huifer.crud.common.utils;

import java.util.Arrays;
import java.util.List;

public class Constant {

  /**
   * gson serialization bean name
   */
  public static final String GSON_SERIALIZATION_BEAN_NAME = "crudGsoSerialization";

  /**
   * jack json serialization bean name
   */
  public static final String JACK_JSON_SERIALIZATION_BEAN_NAME = "crudJackJsonSerialization";

  /**
   * serialization bean names.
   */
  public static final List<String> SERIALIZATION_BEAN_NAMES = Arrays
      .asList(new String[]{GSON_SERIALIZATION_BEAN_NAME,
          JACK_JSON_SERIALIZATION_BEAN_NAME}.clone());

  /**
   * gson config bean name
   */
  public static final String GSON_SETTING_BEAN_NAME = "crudGsonConfigSetting";

  /**
   * jack json config bean name
   */
  public static final String JACK_SERIALIZATION_BEAN_NAME = "jackJsonSetting";

  public static final String SERIALIZATION_CALL_IMPL = "serializationCallImpl";

  private Constant() {
    throw new IllegalStateException("utils class");
  }
}
