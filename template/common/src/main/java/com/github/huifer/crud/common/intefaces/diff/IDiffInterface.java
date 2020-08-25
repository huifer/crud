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

package com.github.huifer.crud.common.intefaces.diff;

import com.github.huifer.crud.common.model.diff.DiffInfoEntity;
import java.util.List;

/**
 * 对象比较接口
 *
 * @param <T> 对象
 */
public interface IDiffInterface<T> {


  /**
   * 对比方法
   *
   * @param source  原始对象
   * @param target  修改后的对象
   * @param logTxId 日志id
   * @return 比较结果
   */
  List<DiffInfoEntity> diff(T source, T target, String logTxId);
}
