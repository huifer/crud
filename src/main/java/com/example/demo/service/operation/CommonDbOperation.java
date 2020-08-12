package com.example.demo.service.operation;

import com.example.demo.mapper.A;
import com.example.demo.service.runner.MapperRunner;

public abstract class CommonDbOperation<T, IdInterface> {

  protected A getA() {
    return MapperRunner.getA(type());
  }

  protected boolean insert(T o) {
    return getA().insert(o) > 0;
  }



  public Class type() {
    throw new RuntimeException("class is null");
  }
}