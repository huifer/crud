package com.example.webdemo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.huifer.crud.interfaces.BaseEntity;

@TableName("issues")
public class IssuesEntity implements BaseEntity {


  @TableId(type = IdType.AUTO)
  private Integer id;
  @TableField(value = "new_title")
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