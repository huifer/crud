package com.github.huifer.mybatis.mapper;

import com.github.huifer.crud.common.annotation.CacheKey;
import com.github.huifer.crud.common.intefaces.A;
import com.github.huifer.mybatis.entity.IssuesEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends A<Integer, IssuesEntity> {

  @Insert("   insert into issue(new_title)values(#{newTitle,jdbcType=VARCHAR})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insertSelective(IssuesEntity record);

  @Select("select id as id , new_title as newTitle from issue where id = #{integer} ")
  IssuesEntity selectByPrimaryKey(Integer integer);

  @Override
  @Update("UPDATE `issue` SET `new_title` = #{newTitle}  WHERE `id` = #{id} ")
  int updateByPrimaryKeySelective(IssuesEntity record);

  @Override
  @Delete("delete from issue where id = #{integer}")
  int deleteByPrimaryKey(Integer integer);
}
