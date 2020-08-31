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

package com.github.huifer.sc.production.mapper;

import com.github.huifer.crud.common.annotation.CacheKey;
import com.github.huifer.crud.common.intefaces.A;
import com.github.huifer.sc.production.entity.ProjectInt;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
@CacheKey(type = ProjectInt.class,key = "asdc")
public interface ProjectIntMapper extends A<Integer, ProjectInt> {

  @Override
  @Insert("INSERT INTO `project_int`(`name`) VALUES (#{name} ) ")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insertSelective(ProjectInt record);

  @Override
  @Select("select * from project_int where id = #{id,javaType=INTEGER} ")
  ProjectInt selectByPrimaryKey(@Param("id") Integer integer);

  @Override
  @Delete("DELETE FROM `dest`.`project_int` WHERE `id` = #{id} ")
  int deleteByPrimaryKey(@Param("id") Integer integer);

  @Override
  @Update("UPDATE `dest`.`project_int` SET `name` = #{name}  WHERE `id`= #{id}  ")
  int updateByPrimaryKeySelective(ProjectInt record);
}
