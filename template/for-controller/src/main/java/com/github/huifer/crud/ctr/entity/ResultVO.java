package com.github.huifer.crud.ctr.entity;

import java.io.Serializable;

/**
 * rest接口返回对象 success
 */
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

  /**
   * 创建成功消息
   *
   * @return status=200, message=null, result=null
   */
  public static ResultVO success() {
    return new ResultVO(SUCCESS, null, null);
  }

  /**
   * 创建成功消息
   *
   * @return status=200, message=null, result=result
   */
  public static ResultVO success(Object result) {
    return new ResultVO(SUCCESS, result, null);
  }

  /**
   * 创建失败消息
   *
   * @return status=500, message=null, result=null
   */
  public static ResultVO failed() {
    return new ResultVO(FAILED, null, null);
  }

  /**
   * 创建失败消息
   *
   * @return status=500, message=message, result=null
   */
  public static ResultVO failed(String message) {
    return new ResultVO(FAILED, null, message);
  }

  /**
   * 创建失败消息
   *
   * @return status=status, message=message, result=null
   */
  public static ResultVO failed(int status, String message) {
    return new ResultVO(status, null, message);
  }

  /**
   * 创建失败消息
   *
   * @return status=status, message=message, result=result
   */
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
