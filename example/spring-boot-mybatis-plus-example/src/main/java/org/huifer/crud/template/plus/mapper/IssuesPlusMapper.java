package org.huifer.crud.template.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.huifer.crud.annotation.CacheKey;
import org.huifer.crud.interfaces.A;
import org.huifer.crud.template.plus.model.IssuesEntity;

@Mapper
@CacheKey(key = "asda",type = IssuesEntity.class)
public interface IssuesPlusMapper extends BaseMapper<IssuesEntity>, A<Integer, IssuesEntity> {

}