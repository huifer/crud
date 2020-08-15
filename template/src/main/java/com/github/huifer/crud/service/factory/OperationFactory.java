package com.github.huifer.crud.service.factory;

import com.github.huifer.crud.daotype.DaoType;

public interface OperationFactory {
  OperationCollection factory(DaoType daoType);
}
