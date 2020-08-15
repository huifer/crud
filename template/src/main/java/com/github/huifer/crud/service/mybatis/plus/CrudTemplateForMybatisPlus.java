package com.github.huifer.crud.service.mybatis.plus;

import com.github.huifer.crud.daotype.DaoType;
import com.github.huifer.crud.interfaces.A;
import com.github.huifer.crud.interfaces.CrudTemplate;
import com.github.huifer.crud.interfaces.DaoTypeLabel;
import com.github.huifer.crud.interfaces.id.IdInterface;
import com.github.huifer.crud.operation.CommonDbOperation;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * mybatis-plus crud template
 *
 * @param <T> entity
 * @param <I> id interface
 */
@Service("crudTemplateForMybatisPlus")
public class CrudTemplateForMybatisPlus<T, I extends IdInterface>
    extends CommonDbOperation<T, I>
    implements CrudTemplate<T, I>, DaoTypeLabel {

  Class<?> type;

  @Override
  public DaoType DAO_TYPE() {
    return DaoType.MYBATIS_PLUS;
  }

  @Override
  public Class type() {
    return this.type;
  }

  @Override
  public boolean insert(T t) {
    type = t.getClass();
    A a = super.getA();
    return a.insert(t) > 0;
  }

  @Override
  public T byId(I i, Class<?> c) {
    this.type = c;
    A a = super.getA();
    return (T) a.selectById((Serializable) i.id());
  }

  @Override
  public boolean del(I i, Class<?> c) {
    A a = super.getA();
    return a.deleteById((Serializable) i.id()) > 0;
  }

  @Override
  public boolean editor(I i, T t) {
    A a = super.getA();
    return a.updateById(t) > 0;
  }


}