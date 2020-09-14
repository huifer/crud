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

package com.github.huifer.crud.common.runner;

import com.github.huifer.crud.common.intefaces.A;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CrudTemplateRunner {

  public static final Map<Class<?>, MapperAndCacheInfo> mapperAndCacheInfoMap = new HashMap<>();


  public static Class<?> mapper(Class<?> a) {
    return mapperAndCacheInfoMap.get(a).getMapperClazz();
  }

  public static A getA(Class<?> a) {
    return mapperAndCacheInfoMap.get(a).getA();
  }

  public static MapperAndCacheInfo getMapperAndCacheInfo(Class clazz) {
    return mapperAndCacheInfoMap.get(clazz);
  }


  protected void afterRunner() {

  }

}