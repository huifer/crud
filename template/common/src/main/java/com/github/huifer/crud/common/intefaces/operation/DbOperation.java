package com.github.huifer.crud.common.intefaces.operation;


import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.intefaces.id.IdInterface;

public interface DbOperation<T, I extends IdInterface> {

  boolean insert(T t, Class<?> c);

  T byId(I interfaces, Class<?> c);


  boolean del(I interfaces);

  boolean editor(I interfaces, T t);

  Class<?> type();

  DaoType DAO_TYPE();
}
