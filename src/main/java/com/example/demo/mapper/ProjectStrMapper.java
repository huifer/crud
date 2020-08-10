package com.example.demo.mapper;

import com.example.demo.entity.ProjectStr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectStrMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProjectStr record);

    int insertSelective(ProjectStr record);

    ProjectStr selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectStr record);

    int updateByPrimaryKey(ProjectStr record);
}