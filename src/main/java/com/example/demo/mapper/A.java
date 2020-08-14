package com.example.demo.mapper;

public interface A<Id, T> {

  int deleteByPrimaryKey(Id id);

  int insertSelective(T record);

  T selectByPrimaryKey(Id id);

  int updateByPrimaryKeySelective(T record);

}
