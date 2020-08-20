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


public class CacheKeyEntity {

  /**
   * redis-hash-key
   */
  private String key;
  /**
   * 实体对象.class
   */
  private Class<?> type;
  /**
   * id字段(从实体对象中获取)
   */
  private String idFiled;
  /**
   * id方法(从实体对象中获取)
   */
  private String idMethod;

  public String getIdMethod() {
    return idMethod;
  }

  public void setIdMethod(String idMethod) {
    this.idMethod = idMethod;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Class<?> getType() {
    return type;
  }

  public void setType(Class<?> type) {
    this.type = type;
  }

  public String getIdFiled() {
    return idFiled;
  }

  public void setIdFiled(String idFiled) {
    this.idFiled = idFiled;
  }
}