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

package com.github.huifer.crud.common.operation;

import com.google.gson.Gson;
import org.springframework.data.redis.core.StringRedisTemplate;

public abstract class RedisHashKeyOperation<T> {

  Gson gson = new Gson();
  private StringRedisTemplate redisTemplate;

  protected void update(String id, T t) {

    T redisObj = this.byId(id);
    if (redisObj != null) {

      // 如果是redis中的类型和当前传入的类型相同
      if (redisObj.getClass().equals(t.getClass())) {
        this.insert(id, t);
      }
    }
  }

  protected void insert(String id, T t) {
    redisTemplate.opsForHash().put(key(), id, gson.toJson(t));
  }

  protected T byId(String id) {
    String o = (String) redisTemplate.opsForHash().get(key(), id);
    return (T) gson.fromJson(o, type());
  }

  protected void delete(String id) {
    this.redisTemplate.opsForHash().delete(key(), id);
  }

  protected Class<?> type() {
    throw new RuntimeException("clazz is null");
  }

  protected String key() {
    throw new RuntimeException("key is null");
  }
}
