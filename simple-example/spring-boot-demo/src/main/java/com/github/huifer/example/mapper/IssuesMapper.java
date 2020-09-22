/*
 *
 * Copyright 2020-2020 HuiFer All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.huifer.example.mapper;

import com.github.huifer.crud.common.annotation.CacheKey;
import com.github.huifer.example.model.IssuesEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper {

  @Insert("   insert into issue(new_title)values(#{newTitle,jdbcType=VARCHAR})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insertSelective(IssuesEntity record);

  @Select("select id as id , new_title as newTitle from issue where id = #{integer} ")
  IssuesEntity selectByPrimaryKey(Integer integer);

  @Update("UPDATE `issue` SET `new_title` = #{newTitle}  WHERE `id` = #{id} ")
  int updateByPrimaryKeySelective(IssuesEntity record);

  @Delete("delete from issue where id = #{integer}")
  int deleteByPrimaryKey(Integer integer);
}