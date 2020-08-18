package com.github.huifer.crud.common.runner;


import com.github.huifer.crud.common.intefaces.A;

public class MapperAndCacheInfo {

  /**
   * 类型
   */
  private Class<?> clazz;
  /**
   * 缓存key
   */
  private String key;
  /**
   * 接口 MapperLabel
   */
  private A a;
  /**
   * mapper 类型
   */
  private Class<?> mapperClazz;

  /**
   * id 字段
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