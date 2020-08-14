package org.huifer.crud.interfaces.operation;


import org.huifer.crud.interfaces.id.IdInterface;

public interface DbOperation<T, I extends IdInterface> {

  boolean insert(T t);

  T byId(I interfaces);


  boolean del(I interfaces);

  boolean editor(I interfaces, T t);

  Class<?> type();
}
