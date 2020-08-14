package com.github.huifer.crud.service;

import com.github.huifer.crud.interfaces.id.IdInterface;
import com.github.huifer.crud.runner.MapperAndCacheInfo;
import com.github.huifer.crud.runner.MapperRunner;
import com.google.gson.Gson;
import com.github.huifer.crud.interfaces.BaseEntity;
import com.github.huifer.crud.interfaces.CrudTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CrudHashTemplate<T extends BaseEntity, I extends IdInterface>
    extends CrudTemplateForMysql<T, I>
    implements CrudTemplate<T, I> {


  Gson gson = new Gson();
  @Autowired
  private StringRedisTemplate redisTemplate;


  @Override
  public boolean insert(T t) {

    boolean insert = super.insert(t);
    if (insert) {
      this.insert(String.valueOf(t.getId()), t);
    }
    return insert;
  }

  @Override
  public T byId(I i, Class c) {
    this.type = c;
    T tre = this.byIdForRedis(String.valueOf(i.id()));
    if (tre != null) {
      return tre;
    }
    else {
      return super.byId(i, c);
    }
  }

  @Override
  public boolean del(I i, Class c) {
    this.type = c;
    boolean del = super.del(i, c);
    this.delete(String.valueOf(i.id()));
    return del;
  }

  @Override
  public boolean editor(I i, T t) {
    this.type = t.getClass();

    this.delete(String.valueOf(i.id()));

    boolean editor = super.editor(i, t);

    if (editor) {

      this.insert(String.valueOf(i.id()), t);
    }

    return editor;
  }


  private void insert(String id, T t) {
    String key = key();
    if (StringUtils.isEmpty(key)) {
      return;
    }

    redisTemplate.opsForHash().put(key, id, gson.toJson(t));
  }

  private T byIdForRedis(String id) {
    String key = key();
    if (StringUtils.isEmpty(key)) {
      return null;
    }

    String o = (String) redisTemplate.opsForHash().get(key(), id);
    return (T) gson.fromJson(o, type());
  }

  private void delete(String id) {
    String key = key();
    if (StringUtils.isEmpty(key)) {
      return;
    }
    this.redisTemplate.opsForHash().delete(key(), id);
  }


  public String key() {
    MapperAndCacheInfo mapperAndCacheInfo = MapperRunner.getMapperAndCacheInfo(type());
    return mapperAndCacheInfo.getKey();
  }

  @Override
  public Class type() {
    // TODO: 2020/8/13 类型获取
    return super.type();
  }
}