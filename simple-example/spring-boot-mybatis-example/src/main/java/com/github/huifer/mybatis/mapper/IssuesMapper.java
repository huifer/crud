package com.github.huifer.mybatis.mapper;

import com.github.huifer.crud.common.annotation.CacheKey;
import com.github.huifer.crud.common.intefaces.A;
import com.github.huifer.mybatis.entity.IssuesEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends A<Integer, IssuesEntity> {

  @Insert("   insert into issue(new_title)values(#{newTitle,jdbcType=VARCHAR})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insertSelective(IssuesEntity record);
}
