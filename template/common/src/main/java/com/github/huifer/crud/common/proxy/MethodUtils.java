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

package com.github.huifer.crud.common.proxy;

import java.lang.reflect.Method;

public class MethodUtils {

  private MethodUtils() {

  }

  public static Method getMethod(Class<?> clazz, String name) {
    Method[] methods = clazz.getMethods();
    return getMethod(methods, name);
  }

  public static Method getMethod(Method[] methods, String name) {
    Method method = null;
    for (Method check : methods) {
      if (check.getName().equals(name)) {
        method = check;
        break;
      }
    }
    return method;
  }


  public static Object invoke(Object o, Method method, Object... params) {
    try {
      return method.invoke(o, params);
    } catch (Exception e) {
      e.printStackTrace();

    }
    return null;
  }

}
