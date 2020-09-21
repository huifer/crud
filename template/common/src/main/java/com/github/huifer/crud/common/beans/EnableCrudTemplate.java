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

package com.github.huifer.crud.common.beans;

import com.github.huifer.crud.common.importselector.CrudTemplateImportSelector;
import com.github.huifer.crud.common.model.enums.JsonEnums;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {Beans.class, CrudTemplateImportSelector.class})
public @interface EnableCrudTemplate {

  /**
   * scan package list
   *
   * @return scan package list
   */
  String[] scanPackages() default {};

  /**
   * json type
   *
   * @return json enums
   */
  JsonEnums jsonEnums() default JsonEnums.GSON;

  /**
   * by id method in mapper
   *
   * @return byId method name
   */
  String selectByIdMethodName();

  /**
   * delete by id method in mapper
   *
   * @return deleteById method name
   */
  String deleteByIdMethodName();

  /**
   * update by id method in mapper
   *
   * @return updateById method name
   */
  String updateByIdMethodName();

  /**
   * insert method in mapper
   *
   * @return insert method name
   */
  String insertMethodName();

}