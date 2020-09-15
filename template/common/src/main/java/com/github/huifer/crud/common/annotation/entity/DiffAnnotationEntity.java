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

/**
 * diff annotation entity
 */
public class DiffAnnotationEntity {

  /**
   * filed name (alias name )
   */
  String name;
  /**
   * change message
   */
  String msg;

  /**
   * mapper.class
   */
  Class<?> mapper;

  /**
   * foreign type
   */
  Class<?> outJoin;
  /**
   * foreign type filed name
   */
  String outField;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Class<?> getMapper() {
    return mapper;
  }

  public void setMapper(Class<?> mapper) {
    this.mapper = mapper;
  }

  public Class<?> getOutJoin() {
    return outJoin;
  }

  public void setOutJoin(Class<?> outJoin) {
    this.outJoin = outJoin;
  }

  public String getOutField() {
    return outField;
  }

  public void setOutField(String outField) {
    this.outField = outField;
  }


}