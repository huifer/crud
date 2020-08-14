package org.huifer.crud.interfaces;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface A<Id, T> extends BaseMapper<T> {


  int deleteByPrimaryKey(Id id);

  int insertSelective(T record);

  T selectByPrimaryKey(Id id);

  int updateByPrimaryKeySelective(T record);

}
