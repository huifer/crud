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

import com.github.huifer.crud.common.intefaces.A;
import com.github.huifer.sc.production.entity.ProjectStr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProjectStrMapper extends A<String, ProjectStr> {

  @Override
  @Insert("INSERT INTO `dest`.`project_str`(`name`,`id`) VALUES (#{name},#{id} ) ")
  int insertSelective(ProjectStr record);

  @Override
  @Select("select * from project_str where id = #{integer} ")
  ProjectStr selectByPrimaryKey(String integer);

  @Override
  @Delete("DELETE FROM `dest`.`project_str` WHERE `id` = #{integer} ")
  int deleteByPrimaryKey(String integer);

  @Override
  @Update("UPDATE `dest`.`project_str` SET `name` = #{name}  WHERE `id`= #{id}  ")
  int updateByPrimaryKeySelective(ProjectStr record);
}
