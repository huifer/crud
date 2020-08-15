package com.github.huifer.crud.service;

import com.github.huifer.crud.daotype.DaoType;
import com.github.huifer.crud.interfaces.BaseEntity;
import com.github.huifer.crud.interfaces.DaoTypeLabel;
import com.github.huifer.crud.interfaces.id.IdInterface;
import com.github.huifer.crud.interfaces.operation.RedisOperation;
import com.github.huifer.crud.runner.MapperAndCacheInfo;
import com.github.huifer.crud.runner.MapperRunner;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @param <T>
 * @param <I>
 */
@Service("crudHashTemplateForRedis")
public class CrudHashTemplateForRedis<T extends BaseEntity, I extends IdInterface> implements RedisOperation<T, I> {
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
    MapperAndCacheInfo mapperAndCacheInfo = MapperRunner.getMapperAndCacheInfo(type());
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
