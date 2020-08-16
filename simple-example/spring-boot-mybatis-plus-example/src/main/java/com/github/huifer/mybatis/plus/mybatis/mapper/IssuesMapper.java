package com.github.huifer.mybatis.plus.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.huifer.crud.common.annotation.CacheKey;
import com.github.huifer.crud.mybatis.plus.interfaces.AforMybatisPlus;
import com.github.huifer.mybatis.plus.mybatis.entity.IssuesEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends BaseMapper<IssuesEntity> , AforMybatisPlus<Integer,IssuesEntity> {

}
