package com.github.huifer.crud.common.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.github.huifer.crud.*"})
public class Beans {

  @Autowired
  private ApplicationContext context;


}