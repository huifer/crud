package com.github.huifer.crud.common.service.facade;

import com.github.huifer.crud.common.annotation.CacheKeyEntity;
import com.github.huifer.crud.common.intefaces.CrudTemplate;
import com.github.huifer.crud.common.intefaces.id.StrIdInterface;
import com.github.huifer.crud.common.runner.CrudScanPackageRunner;
import com.google.gson.Gson;
import java.lang.reflect.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CrudEntityFacade<T> implements CrudTemplate<T, StrIdInterface<String>> {


  Gson gson = new Gson();
  @Autowired
  private StringRedisTemplate redisTemplate;

  private static Object getFiled(Object o, String filed) {
    Class<?> aClass = o.getClass();
    Field[] fields = aClass.getDeclaredFields();
    Object res = null;
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true);
      if (fields[i].getName().equals(filed)) {
        try {
          res = fields[i].get(o);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    return res;
  }

  @Override
  public boolean insert(T t) {

    CacheKeyEntity cacheKeyEntity = CrudScanPackageRunner.cacheKeyEntity(t.getClass());

    redisTemplate.opsForHash()
        .put(cacheKeyEntity.getKey(), String.valueOf(getFiled(t, cacheKeyEntity.getIdFiled())),
            gson.toJson(t));

    return true;
  }

  @Override
  public T byId(StrIdInterface<String> stringStrIdInterface, Class<?> c) {
    CacheKeyEntity cacheKeyEntity = CrudScanPackageRunner.cacheKeyEntity(c);

    String o = (String) redisTemplate.opsForHash()
        .get(cacheKeyEntity.getKey(), stringStrIdInterface.id());
    return (T) gson.fromJson(o, c);
  }

  @Override
  public boolean del(StrIdInterface<String> stringStrIdInterface, Class<?> c) {
    CacheKeyEntity cacheKeyEntity = CrudScanPackageRunner.cacheKeyEntity(c);

    redisTemplate.opsForHash().delete(cacheKeyEntity.getKey(), stringStrIdInterface.id());
    return true;
  }

  @Override
  public boolean editor(T t) {
    CacheKeyEntity cacheKeyEntity = CrudScanPackageRunner.cacheKeyEntity(t.getClass());

    redisTemplate.opsForHash()
        .put(cacheKeyEntity.getKey(), String.valueOf(getFiled(t, cacheKeyEntity.getIdFiled())),
            gson.toJson(t));
    return true;
  }
}