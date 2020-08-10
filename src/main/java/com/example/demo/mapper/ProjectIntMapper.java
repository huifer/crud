package com.example.demo.mapper;

import com.example.demo.entity.ProjectInt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectIntMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectInt record);

    int insertSelective(ProjectInt record);

    ProjectInt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectInt record);

    int updateByPrimaryKey(ProjectInt record);
}