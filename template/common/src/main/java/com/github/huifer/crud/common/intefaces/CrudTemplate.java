package com.github.huifer.crud.common.intefaces;


import com.github.huifer.crud.common.intefaces.id.IdInterface;

public interface CrudTemplate<T, I extends IdInterface> {

  boolean insert(T t);

  T byId(I i, Class<?> c);

  boolean del(I i, Class<?> c);

  boolean editor( T t);

}
