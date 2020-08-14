package com.example.webdemo.mapper;

import com.example.webdemo.entity.IssuesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.huifer.crud.annotation.CacheKey;
import org.huifer.crud.interfaces.A;

@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends A<Integer, IssuesEntity> {

  int insertSelective(IssuesEntity record);
}
