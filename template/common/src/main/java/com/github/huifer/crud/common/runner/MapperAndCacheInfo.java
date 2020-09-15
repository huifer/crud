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

public class MapperAndCacheInfo {

  /**
   * entity class
   */
  private Class<?> clazz;
  /**
   * cache key
   */
  private String key;
  /**
   * {@link A}
   */
  private A a;
  /**
   * mapper.class
   */
  private Class<?> mapperClazz;

  /**
   * id filed name
   */
  private String idField;

  public String getIdField() {
    return idField;
  }

  public void setIdField(String idField) {
    this.idField = idField;
  }

  public Class<?> getMapperClazz() {
    return mapperClazz;
  }

  public void setMapperClazz(Class<?> mapperClazz) {
    this.mapperClazz = mapperClazz;
  }

  public Class<?> getClazz() {
    return clazz;
  }

  public void setClazz(Class<?> clazz) {
    this.clazz = clazz;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public A getA() {
    return a;
  }

  public void setA(A a) {
    this.a = a;
  }
}