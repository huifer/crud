package com.github.huifer.crud.beans;

import com.github.huifer.crud.daotype.DaoType;
import com.github.huifer.crud.daotype.DaoTypeThreadLocal;
import com.github.huifer.crud.interfaces.BaseEntity;
import com.github.huifer.crud.interfaces.CrudTemplate;
import com.github.huifer.crud.interfaces.id.IdInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.github.huifer.crud.*"})
public class Beans {

  @Autowired
  private ApplicationContext context;

//  @Autowired
//  @Qualifier("crudFacadeForMybatis")
//  private CrudTemplate<BaseEntity, IdInterface<Object>> crudFacadeForMybatis;
//
//  @Bean
//  public CrudTemplate crudTemplate() {
//    DaoType daoType = DaoTypeThreadLocal.getDaoType();
//    return crudFacadeForMybatis;
//  }


}