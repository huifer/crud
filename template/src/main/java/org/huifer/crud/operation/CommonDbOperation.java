package org.huifer.crud.operation;

import org.huifer.crud.interfaces.A;
import org.huifer.crud.interfaces.id.IdInterface;
import org.huifer.crud.runner.MapperRunner;

public class CommonDbOperation<T, I extends IdInterface> {

  Class<?> type;


  public A getA() {
    return MapperRunner.getA(type());
  }

  public boolean insert(T o) {
    this.type = o.getClass();
    return getA().insertSelective(o) > 0;
  }

  public T byId(I idInterface, Class c) {
    this.type = c;
    return (T) getA().selectByPrimaryKey(idInterface.id());
  }

  public boolean del(I id, Class c) {
    this.type = c;
    return getA().deleteByPrimaryKey(id.id()) > 0;
  }

  public boolean update(T t) {
    this.type = t.getClass();
    return getA().updateByPrimaryKeySelective(t) > 0;
  }

  public Class type() {
    return this.type;
  }
}