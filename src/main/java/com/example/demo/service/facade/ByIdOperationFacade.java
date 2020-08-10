package com.example.demo.service.facade;

import com.example.demo.service.id.IdInterface;
import com.example.demo.service.operation.DbOperation;

public interface ByIdOperationFacade<T, I extends IdInterface> {

  boolean insert(T t);

  T byId(I i);

  boolean del(I i);

  boolean editor(I i, T t);

  DbOperation getDbOperation();

}
