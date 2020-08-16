package com.github.huifer.crud.mybatis.plus.runner;

import com.github.huifer.crud.common.runner.MapperAndCacheInfo;
import com.github.huifer.crud.mybatis.plus.interfaces.AforMybatisPlus;

public class MapperAndCacheInfoForMybatisPlus extends MapperAndCacheInfo {
  private AforMybatisPlus forMybatisPlus;

  public AforMybatisPlus getForMybatisPlus() {
    return forMybatisPlus;
  }

  public void setForMybatisPlus(AforMybatisPlus forMybatisPlus) {
    this.forMybatisPlus = forMybatisPlus;
  }
}
