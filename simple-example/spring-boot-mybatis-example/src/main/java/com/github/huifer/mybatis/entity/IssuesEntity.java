package com.github.huifer.mybatis.entity;


import com.github.huifer.crud.common.intefaces.BaseEntity;

public class IssuesEntity implements BaseEntity {


  private Integer id;
  private String newTitle;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNewTitle() {
    return newTitle;
  }

  public void setNewTitle(String newTitle) {
    this.newTitle = newTitle;
  }
}