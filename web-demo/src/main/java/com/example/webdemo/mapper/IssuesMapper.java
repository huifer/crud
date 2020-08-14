package com.example.webdemo.mapper;

import com.example.demo.ann.CacheKey;
import com.example.demo.entity.ProjectInt;
import com.example.demo.mapper.A;
import com.example.webdemo.entity.IssuesEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends A<Integer, IssuesEntity> {

  int insertSelective(IssuesEntity record);
}
