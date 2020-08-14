package com.github.huifer.crud.interfaces;


import com.github.huifer.crud.interfaces.id.IdInterface;

public interface CrudTemplate<T, I extends IdInterface> {

  boolean insert(T t);

  T byId(I i, Class c);

  boolean del(I i, Class c);

  boolean editor(I i, T t);

}
