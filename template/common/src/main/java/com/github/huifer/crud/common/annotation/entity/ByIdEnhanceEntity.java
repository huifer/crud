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

package com.github.huifer.crud.common.annotation.entity;

public class ByIdEnhanceEntity {

  private String foreignKey;
  private String queryMethod;
  private Class<?> mapper;

  public String getForeignKey() {
    return foreignKey;
  }

  public void setForeignKey(String foreignKey) {
    this.foreignKey = foreignKey;
  }

  public String getQueryMethod() {
    return queryMethod;
  }

  public void setQueryMethod(String queryMethod) {
    this.queryMethod = queryMethod;
  }

  public Class<?> getMapper() {
    return mapper;
  }

  public void setMapper(Class<?> mapper) {
    this.mapper = mapper;
  }

}
