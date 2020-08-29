package com.github.huifer.crud.ctr.entity;


import com.github.huifer.crud.common.intefaces.BaseEntity;

public class AbsEntity implements BaseEntity {

  private Object id;

  @Override
  public Object getId() {
    return id;
  }

  public void setId(Object id) {
    this.id = id;
  }
}
