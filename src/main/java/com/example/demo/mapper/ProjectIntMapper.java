package com.example.demo.mapper;

import com.example.demo.entity.ProjectInt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectIntMapper extends A<Integer, ProjectInt> {

  int deleteByPrimaryKey(Integer integer);

  int insert(ProjectInt record);

  int insertSelective(ProjectInt record);

  ProjectInt selectByPrimaryKey(Integer integer);

  int updateByPrimaryKeySelective(ProjectInt record);

  int updateByPrimaryKey(ProjectInt record);
}