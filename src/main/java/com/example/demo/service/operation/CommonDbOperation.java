package com.example.demo.service.operation;

import com.example.demo.mapper.A;
import com.example.demo.service.runner.MapperRunner;
import org.springframework.stereotype.Service;

@Service
public class CommonDbOperation<T, I extends com.example.demo.service.id.IdInterface> {

  public A getA() {
    return MapperRunner.getA(type());
  }

  public boolean insert(T o) {
    return getA().insert(o) > 0;
  }

  public T byId(I idInterface) {
    return (T) getA().selectByPrimaryKey(idInterface.id());
  }


  public Class type() {
    throw new RuntimeException("class is null");
  }
}