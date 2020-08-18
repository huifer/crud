package com.github.huifer.crud.common.service.facade;

import com.github.huifer.crud.common.annotation.CacheKeyEntity;
import com.github.huifer.crud.common.intefaces.CrudTemplate;
import com.github.huifer.crud.common.intefaces.id.StrIdInterface;
import com.github.huifer.crud.common.runner.CrudScanPackageRunner;
import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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


  private static String key(Object o, String method) {

    try {
      Class<?> aClass = o.getClass();
      if (!StringUtils.isEmpty(method)) {

        Method method1 = aClass.getDeclaredMethod(method);
        method1.setAccessible(true);
        Object invoke = method1.invoke(o);
        return String.valueOf(invoke);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  private static String key(Object o, String filed, String method) {

    String res = "";
    if (StringUtils.isEmpty(res)) {
      res = key(o, method);
    }
    Object k1 = getFiled(o, filed);
    res = String.valueOf(k1);


    if (StringUtils.isEmpty(res)) {
      throw new NullPointerException("key 不能为空");
    }
    return res;

  }

  @Override
  public boolean insert(T t) {

    CacheKeyEntity cacheKeyEntity = CrudScanPackageRunner.cacheKeyEntity(t.getClass());

    redisTemplate.opsForHash()
        .put(cacheKeyEntity.getKey(),
            String.valueOf(key(t, cacheKeyEntity.getIdFiled(), cacheKeyEntity.getIdMethod())),
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
        .put(cacheKeyEntity.getKey(),
            key(t, cacheKeyEntity.getIdFiled(), cacheKeyEntity.getIdMethod()),
            gson.toJson(t));
    return true;
  }
}