package com.github.huifer.crud.common.intefaces.diff;

import com.github.huifer.crud.common.model.diff.DiffInfoEntity;
import java.util.List;

public interface IDiffInterface<T> {


  /**
   * 对比方法
   *
   * @param source  原始对象
   * @param target  修改后的对象
   * @param logTxId 日志id
   */
  List<DiffInfoEntity> diff(T source, T target, String logTxId);
}
