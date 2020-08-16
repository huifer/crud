package com.github.huifer.crud.jpa.service;

import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.intefaces.CrudTemplate;
import com.github.huifer.crud.common.intefaces.DaoTypeLabel;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.operation.CommonDbOperation;
import com.github.huifer.crud.jpa.interfaces.AforJpa;
import org.springframework.stereotype.Service;

import java.io.Serializable;

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
    AforJpa a = (AforJpa) super.getA();
    return a.save(t) != null;
  }

  @Override
  public T byId(I i, Class<?> c) {
    this.type = c;
    AforJpa a = (AforJpa) super.getA();

    return (T) a.findById((Serializable) i.id()).get();
  }

  @Override
  public boolean del(I i, Class<?> c) {

    AforJpa a = (AforJpa) super.getA();
    return a.deleteByPrimaryKey(i.id()) > 0;
  }

  @Override
  public boolean editor(I i, T t) {


    AforJpa a = (AforJpa) super.getA();

    return a.updateByPrimaryKeySelective(t) > 0;
  }


}