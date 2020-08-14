package com.example.demo.mapper;

import com.example.demo.ann.CacheKey;
import com.example.demo.entity.ProjectInt;
import com.example.demo.entity.ProjectStr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheKey(key = "ann:project:str",type = ProjectStr.class)
public interface ProjectStrMapper extends A<String, ProjectStr> {

  int deleteByPrimaryKey(String id);

  int insert(ProjectStr record);

  int insertSelective(ProjectStr record);

  ProjectStr selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(ProjectStr record);

  int updateByPrimaryKey(ProjectStr record);


}
