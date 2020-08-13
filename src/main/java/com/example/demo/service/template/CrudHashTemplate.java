package com.example.demo.service.template;

import com.example.demo.entity.BaseEntity;
import com.example.demo.enums.CacheTable;
import com.example.demo.service.id.IdInterface;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("crudHashTemplate")
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
// TODO: 2020/8/13 key
    return CacheTable.key(type());
  }

  @Override
  public Class type() {
    // TODO: 2020/8/13 类型获取
    return super.type();
  }
}