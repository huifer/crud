package com.github.huifer.crud.mybatis.service;


import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.intefaces.CrudTemplate;
import com.github.huifer.crud.common.intefaces.DaoTypeLabel;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.operation.CommonDbOperation;
import org.springframework.stereotype.Service;

/**
 * com.github.huifer.mybatis.plus.mybatis crud template
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
  public boolean editor(T t) {
    this.type = t.getClass();
    return super.update(t);
  }


}