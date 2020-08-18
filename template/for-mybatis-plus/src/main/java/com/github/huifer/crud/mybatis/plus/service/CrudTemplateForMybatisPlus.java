package com.github.huifer.crud.mybatis.plus.service;

import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.intefaces.CrudTemplate;
import com.github.huifer.crud.common.intefaces.DaoTypeLabel;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.operation.CommonDbOperation;
import com.github.huifer.crud.mybatis.plus.interfaces.AforMybatisPlus;
import java.io.Serializable;
import org.springframework.stereotype.Service;

/**
 * com.github.huifer.mybatis.plus.mybatis-plus crud template
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
    AforMybatisPlus a = (AforMybatisPlus) super.getA();
    return a.insert(t) > 0;
  }

  @Override
  public T byId(I i, Class<?> c) {
    this.type = c;
    AforMybatisPlus a = (AforMybatisPlus) super.getA();

    return (T) a.selectById((Serializable) i.id());
  }

  @Override
  public boolean del(I i, Class<?> c) {

    AforMybatisPlus a = (AforMybatisPlus) super.getA();

    return a.deleteById((Serializable) i.id()) > 0;
  }

  @Override
  public boolean editor( T t) {
    AforMybatisPlus a = (AforMybatisPlus) super.getA();
    return a.updateById(t) > 0;
  }


}