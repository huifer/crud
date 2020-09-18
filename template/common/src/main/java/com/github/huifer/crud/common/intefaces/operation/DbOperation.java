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

package com.github.huifer.crud.common.intefaces.operation;


import com.github.huifer.crud.common.intefaces.BaseEntity;
import com.github.huifer.crud.common.intefaces.id.IdInterface;

/**
 * db operation interface
 *
 * @param <T> entity
 * @param <I> id type
 * @see BaseEntity
 * @see IdInterface
 */
public interface DbOperation<T extends BaseEntity, I extends IdInterface> {

  <T extends BaseEntity>  boolean insert(T t, Class<?> c);

  T byId(I interfaces, Class<?> c);


  boolean del(I interfaces);

  boolean editor(I interfaces, T t);

  Class<?> type();

}
