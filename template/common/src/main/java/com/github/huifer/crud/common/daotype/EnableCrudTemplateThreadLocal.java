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

package com.github.huifer.crud.common.daotype;

import java.util.Arrays;
import java.util.List;

public class EnableCrudTemplateThreadLocal {

  private static final ThreadLocal<DaoType> daoTypeThreadLocal = new ThreadLocal<>();
  private static final ThreadLocal<List<String>> scanPackages = new ThreadLocal<>();


  private EnableCrudTemplateThreadLocal() throws IllegalAccessException {
    throw new IllegalAccessException("this is a utils !");
  }

  /**
   * get scan packages from thread local
   *
   * @return scan package
   */
  public static List<String> getPackages() {
    return scanPackages.get();
  }

  /**
   * setting scan packages
   *
   * @param packages packages
   */
  public static void setScanPackages(String[] packages) {
    scanPackages.set(Arrays.asList(packages));
  }

  /**
   * get dao type
   *
   * @return daoTYpe
   */
  public static DaoType getDaoType() {
    return daoTypeThreadLocal.get();
  }

  /**
   * setting dao type
   *
   * @param daoType dao type
   */
  public static void setDaoType(DaoType daoType) {
    DaoType cache = daoTypeThreadLocal.get();
    if (cache == null) {
      daoTypeThreadLocal.set(daoType);
    }
  }

}
