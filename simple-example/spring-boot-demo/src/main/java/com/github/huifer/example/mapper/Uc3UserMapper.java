package com.github.huifer.example.mapper;

import com.github.huifer.crud.common.annotation.CacheKey;
import com.github.huifer.example.model.Uc3User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
@CacheKey(key = "aaaaaa", type = Uc3User.class)
public interface Uc3UserMapper {

  @Delete("    delete from uc_user\n" +
      "    where ID = #{id,jdbcType=INTEGER}")
  int deleteByPrimaryKey(Integer id);

  @Insert("   insert into uc_user(name)values(#{name} )")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insertSelective(Uc3User record);


  @Select("select * from uc_user where id = #{id} ")
  Uc3User selectByPrimaryKey(Integer id);

  @Update(" update uc_user SET NAME = #{name} , AVATAR = #{avatar}  where id = #{id}  ")
  int updateByPrimaryKeySelective(Uc3User record);

}