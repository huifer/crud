package com.github.huifer.crud.common.operation;


import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.intefaces.A;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.intefaces.operation.DbOperation;
import com.github.huifer.crud.common.runner.CrudTemplateRunner;

public class CommonDbOperation<T, I extends IdInterface> implements DbOperation<T, I> {

  Class<?> type;

  @Override
  public DaoType DAO_TYPE() {
    return null;
  }

  public A getA() {
    return CrudTemplateRunner.getA(type());
  }

  @Override
  public boolean del(I interfaces) {
    return getA().deleteByPrimaryKey(interfaces.id()) > 0;
  }

  @Override
  public boolean editor(I interfaces, T t) {
    return this.update(t);
  }

  public boolean insert(T o, Class<?> c) {
    this.type = o.getClass();
    return getA().insertSelective(o) > 0;
  }

  public T byId(I idInterface, Class<?> c) {
    this.type = c;
    return (T) getA().selectByPrimaryKey(idInterface.id());
  }

  public boolean del(I id, Class<?> c) {
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