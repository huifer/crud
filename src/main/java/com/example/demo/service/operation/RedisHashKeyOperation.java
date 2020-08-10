package com.example.demo.service.operation;

import com.google.gson.Gson;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisHashKeyOperation<T> {

  Gson gson = new Gson();
  @Autowired
  private StringRedisTemplate redisTemplate;

  protected void update(String key, String id, T t) {

    T redisObj = this.byId(key, id, t.getClass());
    if (!Objects.isNull(redisObj)) {

      // 如果是redis中的类型和当前传入的类型相同
      if (redisObj.getClass().equals(t.getClass())) {
        this.insert(key, id, t);
      }
    }
  }

  protected void insert(String key, String id, T t) {
    redisTemplate.opsForHash().put(key, id, gson.toJson(t));
  }

  protected T byId(String key, String id, Class<?> clazz) {
    String o = (String) redisTemplate.opsForHash().get(key, id);
    return (T) gson.fromJson(o, clazz);
  }

  protected void delete(String key, String id) {
    this.redisTemplate.opsForHash().delete(key, id);
  }
}
