package com.github.huifer.crud.interfaces.operation;


import com.github.huifer.crud.daotype.DaoType;
import com.github.huifer.crud.interfaces.id.IdInterface;

public interface DbOperation<T, I extends IdInterface> {

  boolean insert(T t, Class<?> c);

  T byId(I interfaces, Class<?> c);


  boolean del(I interfaces);

  boolean editor(I interfaces, T t);

  Class<?> type();

  DaoType DAO_TYPE();
}
