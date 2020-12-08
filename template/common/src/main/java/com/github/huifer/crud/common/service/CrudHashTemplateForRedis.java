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

import static com.github.huifer.crud.common.utils.Constant.CRUD_HASH_TEMPLATE_FOR_REDIS_BEAN_NAME;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.intefaces.operation.RedisOperation;
import com.github.huifer.crud.common.runner.MapperSuperRunner;
import com.github.huifer.crud.common.serialize.SerializationCall;
import com.github.huifer.crud.common.utils.Constant;
import com.github.huifer.crud.common.utils.ThreadLocalHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service(CRUD_HASH_TEMPLATE_FOR_REDIS_BEAN_NAME)
public class CrudHashTemplateForRedis implements
    RedisOperation {

  Class<?> type;

  @Autowired
  @Qualifier(Constant.SERIALIZATION_CALL_IMPL)
  SerializationCall serializationCall;

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Override
  public <T> void update(IdInterface id, T t) {
    this.insert(t, id);
  }

  public <T> void insert(T t, IdInterface id) {
    String key = key();
    if (StringUtils.isEmpty(key)) {
      return;
    }

    redisTemplate.opsForHash().put(key, String.valueOf(id.id()), objToJson(t));
  }

  private <T> String objToJson(T t) {
    return serializationCall.toJson(t);
  }


  public <T> T byId(IdInterface id) {
    String key = key();
    if (StringUtils.isEmpty(key)) {
      return null;
    }

    if (redisTemplate != null) {
      String o = (String) redisTemplate.opsForHash().get(key(), String.valueOf(id.id()));
      return (T) toObj(o);
    }
    return null;
  }

  private Object toObj(String o) {
    return serializationCall.fromJson(o, type());
  }

  public void del(IdInterface id) {
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
    return MapperSuperRunner.getCacheKey(type);
  }

  @Override
  public void setClass(Class<?> clazz) {
    ThreadLocalHolder.setClassThreadLocal(clazz);

  }

  public Class type() {
    return ThreadLocalHolder.getClassThreadLocal();
  }
}
