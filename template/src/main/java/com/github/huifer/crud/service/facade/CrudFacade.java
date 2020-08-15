package com.github.huifer.crud.service.facade;


import com.github.huifer.crud.daotype.DaoTypeThreadLocal;
import com.github.huifer.crud.interfaces.BaseEntity;
import com.github.huifer.crud.interfaces.CrudTemplate;
import com.github.huifer.crud.interfaces.id.IdInterface;
import com.github.huifer.crud.interfaces.id.StrIdInterface;
import com.github.huifer.crud.interfaces.operation.RedisOperation;
import com.github.huifer.crud.service.factory.OperationCollection;
import com.github.huifer.crud.service.factory.OperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CrudFacade<T extends BaseEntity, I extends IdInterface>
    implements CrudTemplate<T, I>
     {
  @Autowired
  @Qualifier("operationFactoryImpl")
  private OperationFactory factory;


  public boolean insert(T t) {

    boolean insert = false;

    OperationCollection factory = this.factory.factory(DaoTypeThreadLocal.getDaoType());
    CrudTemplate dbOperation = factory.getDbOperation();
    if (dbOperation != null) {
      insert = dbOperation.insert(t);
    }

    RedisOperation redisOperation = factory.getRedisOperation();

    if (redisOperation != null) {
      redisOperation.setClass(t.getClass());
      redisOperation.insert(t, (StrIdInterface) () -> String.valueOf(t.getId()));
    }

    return insert;
  }

  public T byId(I i, Class<?> c) {
    OperationCollection factory = this.factory.factory(DaoTypeThreadLocal.getDaoType());

    RedisOperation redisOperation = factory.getRedisOperation();

    if (redisOperation != null) {
      redisOperation.setClass(c);
      return (T) redisOperation.byId(i);
    }

    CrudTemplate dbOperation = factory.getDbOperation();

    if (dbOperation != null) {
      return (T) dbOperation.byId(i, c);
    }

    return null;
  }

  public boolean del(I i, Class<?> c) {
    boolean del = false;
    OperationCollection factory = this.factory.factory(DaoTypeThreadLocal.getDaoType());
    RedisOperation redisOperation = factory.getRedisOperation();
    if (redisOperation != null) {
      redisOperation.setClass(c);
      redisOperation.del(i);
    }

    CrudTemplate dbOperation = factory.getDbOperation();

    if (dbOperation != null) {
      redisOperation.setClass(c);
      del = dbOperation.del(i, c);
    }


    return del;
  }

  public boolean editor(I i, T t) {

    boolean editor = false;
    OperationCollection factory = this.factory.factory(DaoTypeThreadLocal.getDaoType());

    RedisOperation redisOperation = factory.getRedisOperation();
    if (redisOperation != null) {
      redisOperation.del(i);
    }

    CrudTemplate dbOperation = factory.getDbOperation();

    if (dbOperation != null) {
      editor = dbOperation.editor(i, t);
    }

    if (redisOperation != null) {
      redisOperation.setClass(t.getClass());
      redisOperation.update(i, t);
    }

    return editor;
  }
}
