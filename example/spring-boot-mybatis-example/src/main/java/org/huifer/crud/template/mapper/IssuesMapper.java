package org.huifer.crud.template.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.huifer.crud.template.model.IssuesEntity;
import org.springframework.stereotype.Service;

@Mapper
public interface IssuesMapper {

  int insertSelective(IssuesEntity record);
}