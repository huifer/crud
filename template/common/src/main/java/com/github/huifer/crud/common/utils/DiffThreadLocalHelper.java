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

import com.github.huifer.crud.common.beans.EnableDiff;
import java.util.Arrays;
import java.util.List;

public class DiffThreadLocalHelper {

  private static final ThreadLocal<List<String>> SCAN_PACKAGE = new ThreadLocal<>();
  private static final ThreadLocal<String> BY_ID_METHOD = new ThreadLocal<>();

  public static String getIdMethod() {
    return BY_ID_METHOD.get();
  }

  public static void setByIdMethod(String idMethod) {
    BY_ID_METHOD.set(idMethod);
  }

  public static List<String> getScan() {
    return SCAN_PACKAGE.get();
  }

  public static void setScan(String[] packages) {
    SCAN_PACKAGE.set(Arrays.asList(packages));
  }
}