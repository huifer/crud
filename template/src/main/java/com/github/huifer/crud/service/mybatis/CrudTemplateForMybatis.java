package com.github.huifer.crud.service.mybatis;

import com.github.huifer.crud.daotype.DaoType;
import com.github.huifer.crud.interfaces.CrudTemplate;
import com.github.huifer.crud.interfaces.DaoTypeLabel;
import com.github.huifer.crud.interfaces.id.IdInterface;
import com.github.huifer.crud.operation.CommonDbOperation;
import org.springframework.stereotype.Service;

/**
 * mybatis crud template
 *
 * @param <T> entity
 * @param <I> id interface
 */
@Service("crudTemplateForMybatis")
public class CrudTemplateForMybatis<T, I extends IdInterface>
    extends CommonDbOperation<T, I>
    implements CrudTemplate<T, I>, DaoTypeLabel {

  Class<?> type;

  @Override
  public DaoType DAO_TYPE() {
    return DaoType.MYBATIS;
  }

  @Override
  public Class type() {
    return this.type;
  }

  @Override
  public boolean insert(T t) {
    type = t.getClass();
    return super.insert(t, type);
  }

  @Override
  public T byId(I i, Class<?> c) {
    this.type = c;
    return super.byId(i, c);
  }

  @Override
  public boolean del(I i, Class<?> c) {
    this.type = c;
    return super.del(i, c);
  }

  @Override
  public boolean editor(I i, T t) {
    this.type = t.getClass();
    return super.update(t);
  }


}