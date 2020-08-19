package com.github.huifer.crud.common.annotation.entity;

import com.github.huifer.crud.common.annotation.CacheKey;

/**
 * @see CacheKey
 */
public class CacheKeyEntity {

  private String key;
  private Class<?> type;
  private String idFiled;
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