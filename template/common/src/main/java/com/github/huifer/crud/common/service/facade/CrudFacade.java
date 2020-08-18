package com.github.huifer.crud.common.service.facade;


import com.github.huifer.crud.common.daotype.EnableCrudTemplateThreadLocal;
import com.github.huifer.crud.common.intefaces.BaseEntity;
import com.github.huifer.crud.common.intefaces.CrudTemplate;
import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.intefaces.id.StrIdInterface;
import com.github.huifer.crud.common.intefaces.operation.RedisOperation;
import com.github.huifer.crud.common.service.factory.OperationCollection;
import com.github.huifer.crud.common.service.factory.OperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CrudFacade<T extends BaseEntity, I extends IdInterface>
    implements CrudTemplate<T, I> {

  @Autowired
  @Qualifier("operationFactoryImpl")
  private OperationFactory factory;


  public boolean insert(T t) {

    boolean insert = false;

    OperationCollection factory = this.factory.factory(EnableCrudTemplateThreadLocal.getDaoType());
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
    OperationCollection factory = this.factory.factory(EnableCrudTemplateThreadLocal.getDaoType());

    RedisOperation redisOperation = factory.getRedisOperation();

    T result = null;

    if (redisOperation != null) {
      redisOperation.setClass(c);
      result = (T) redisOperation.byId(i);
    }

    CrudTemplate dbOperation = factory.getDbOperation();

    if (result == null) {

      if (dbOperation != null) {
        result = (T) dbOperation.byId(i, c);

        if (result != null) {
          if (redisOperation != null) {
            redisOperation.setClass(c);
            T finalResult = result;
            redisOperation.insert(result,
                (StrIdInterface) () -> String.valueOf(finalResult.getId()));
          }
        }
      }
    }

    return result;
  }

  public boolean del(I i, Class<?> c) {
    boolean del = false;
    OperationCollection factory = this.factory.factory(EnableCrudTemplateThreadLocal.getDaoType());
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

  public boolean editor(T t) {

    boolean editor = false;
    OperationCollection factory = this.factory.factory(EnableCrudTemplateThreadLocal.getDaoType());

    RedisOperation redisOperation = factory.getRedisOperation();
    if (redisOperation != null) {
      redisOperation.del(new IdInterface() {
        @Override
        public Object id() {
          return t.getId();
        }
      });
    }

    CrudTemplate dbOperation = factory.getDbOperation();

    if (dbOperation != null) {
      editor = dbOperation.editor(t);
    }

    if (redisOperation != null) {
      redisOperation.setClass(t.getClass());
      redisOperation.update(new StrIdInterface() {
        @Override
        public String id() {
          return String.valueOf(t.getId());
        }
      }, t);
    }

    return editor;
  }
}
