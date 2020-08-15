package com.example.webdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webdemo.entity.IssuesEntity;
import com.github.huifer.crud.annotation.CacheKey;
import com.github.huifer.crud.interfaces.A;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends BaseMapper<IssuesEntity>, A<Integer, IssuesEntity> {

  @Insert("   insert into issue(new_title)values(#{newTitle,jdbcType=VARCHAR})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insertSelective(IssuesEntity record);
}
