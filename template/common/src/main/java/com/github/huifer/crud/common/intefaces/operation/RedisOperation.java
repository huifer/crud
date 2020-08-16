package com.github.huifer.crud.common.intefaces.operation;


import com.github.huifer.crud.common.intefaces.id.IdInterface;

public interface RedisOperation<T, I extends IdInterface> {

  void insert(T t, I id);


  void update(I id, T t);

  void del(I id);

  T byId(I id);

  Class<?> type();

  void setClass(Class<?> clazz);

  String key();
}
