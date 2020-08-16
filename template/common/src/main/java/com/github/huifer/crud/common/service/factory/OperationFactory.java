package com.github.huifer.crud.common.service.factory;


import com.github.huifer.crud.common.daotype.DaoType;

public interface OperationFactory {
  OperationCollection factory(DaoType daoType);
}
