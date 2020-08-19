package com.github.huifer.crud.common.annotation.entity;

import com.github.huifer.crud.common.annotation.DiffAnnotation;

/**
 * @see DiffAnnotation
 */
public class DiffAnnotationEntity {

  String name;

  String msg;

  Class<?> mapper;

  Class<?> outJoin;
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