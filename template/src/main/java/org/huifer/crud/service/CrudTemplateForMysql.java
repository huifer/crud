package org.huifer.crud.service;

import org.huifer.crud.interfaces.CrudTemplate;
import org.huifer.crud.interfaces.id.IdInterface;
import org.huifer.crud.operation.CommonDbOperation;
import org.springframework.stereotype.Service;

public class CrudTemplateForMysql<T, I extends IdInterface>
    extends CommonDbOperation<T, I>
    implements CrudTemplate<T, I> {

  Class<?> type;

  @Override
  public Class type() {
    return this.type;
  }

  @Override
  public boolean insert(T t) {
    type = t.getClass();
    return super.insert(t);
  }

  @Override
  public T byId(I i, Class c) {
    this.type = c;
    return super.byId(i, c);
  }

  @Override
  public boolean del(I i, Class c) {

    return super.del(i, c);
  }

  @Override
  public boolean editor(I i, T t) {
    return super.update(t);
  }


}