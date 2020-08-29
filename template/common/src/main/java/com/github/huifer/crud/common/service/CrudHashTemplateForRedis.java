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

package com.github.huifer.crud.common.service;

import com.github.huifer.crud.common.intefaces.BaseEntity;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.intefaces.operation.RedisOperation;
import com.github.huifer.crud.common.runner.CrudTemplateRunner;
import com.github.huifer.crud.common.runner.MapperAndCacheInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @param <T> entity
 * @param <I> id interface
 */
@Service("crudHashTemplateForRedis")
public class CrudHashTemplateForRedis<T extends BaseEntity, I extends IdInterface> implements
    RedisOperation<T, I> {

  Gson gson = new Gson();
  Class<?> type;
  @Autowired
  private StringRedisTemplate redisTemplate;


  @Override
  public void update(I id, T t) {
    this.insert(t, id);
  }

  public void insert(T t, I id) {
    String key = key();
    if (StringUtils.isEmpty(key)) {
      return;
    }

    redisTemplate.opsForHash().put(key, String.valueOf(id.id()), gson.toJson(t));
  }

  public T byId(I id) {
    String key = key();
    if (StringUtils.isEmpty(key)) {
      return null;
    }

    if (redisTemplate != null) {
      String o = (String) redisTemplate.opsForHash().get(key(), String.valueOf(id.id()));
      return (T) gson.fromJson(o, type());
    }
    return null;
  }

  public void del(I id) {
    String key = key();
    if (StringUtils.isEmpty(key)) {
      return;
    }
    if (redisTemplate != null) {
      this.redisTemplate.opsForHash().delete(key(), String.valueOf(id.id()));
    }
  }


  public String key() {
    Class type = type();
    if (type == null) {
      return "";
    }
    MapperAndCacheInfo mapperAndCacheInfo = CrudTemplateRunner.getMapperAndCacheInfo(type);
    if (mapperAndCacheInfo == null) {
      return "";
    }
    return mapperAndCacheInfo.getKey();
  }

  @Override
  public void setClass(Class<?> clazz) {
    this.type = clazz;
  }

  public Class type() {
    return this.type;
  }
}
