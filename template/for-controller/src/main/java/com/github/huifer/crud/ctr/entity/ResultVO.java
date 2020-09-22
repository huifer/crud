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

package com.github.huifer.crud.ctr.entity;

import java.io.Serializable;

public class ResultVO implements Serializable {

  public static final int SUCCESS = 200;
  public static final int FAILED = 500;
  private Object result;
  private int status;
  private String message;

  public ResultVO() {
    this(FAILED, null, null);
  }

  public ResultVO(int status, Object result, String message) {
    super();
    this.status = status;
    this.result = result;
    this.message = message;
  }

  public static ResultVO success() {
    return new ResultVO(SUCCESS, null, null);
  }

  public static ResultVO success(Object result) {
    return new ResultVO(SUCCESS, result, null);
  }

  public static ResultVO failed() {
    return new ResultVO(FAILED, null, null);
  }

  public static ResultVO failed(String message) {
    return new ResultVO(FAILED, null, message);
  }

  public static ResultVO failed(int status, String message) {
    return new ResultVO(status, null, message);
  }

  public static ResultVO failed(int status, String message, Object result) {
    return new ResultVO(status, result, message);
  }

  public boolean isSuccess() {
    return SUCCESS == this.status;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ResultVO{"
        + "result="
        + result
        + ", status="
        + status
        + ", message='"
        + message
        + '\''
        + '}';
  }
}
