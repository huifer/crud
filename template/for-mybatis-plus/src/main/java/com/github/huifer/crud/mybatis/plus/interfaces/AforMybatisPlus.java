package com.github.huifer.crud.mybatis.plus.interfaces;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.huifer.crud.common.intefaces.A;

public interface AforMybatisPlus<Id, T> extends BaseMapper<T>, A<Id, T> {


}
