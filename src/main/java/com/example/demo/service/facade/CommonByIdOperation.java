package com.example.demo.service.facade;

import com.example.demo.service.id.IdInterface;
import com.example.demo.service.operation.DbOperation;
import com.example.demo.service.operation.RedisOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class CommonByIdOperation<T, I extends IdInterface>
    implements ByIdOperationFacade<T, I> {

  @Autowired
  @Qualifier("ByIdOperationFactoryImpl")
  ByIdOperationFactory byIdOperationFactory;

  @Override
  public boolean insert(T t) {
    DbOperation dbOperation = this.getDbOperation();
    boolean insert = false;
    if (dbOperation != null) {
      insert = dbOperation.insert(t);
    }
    RedisOperation redisOperation = this.operationCollection().getRedisOperation();
    if (redisOperation != null) {
      redisOperation.insert(t);
    }
    return insert;
  }

  @Override
  public DbOperation getDbOperation() {
    throw new RuntimeException("没有实现");
  }

  public boolean editor(I i, T t) {

    boolean editor = false;

    OperationCollections operationCollections = this.operationCollection();
    RedisOperation redisOperation = operationCollections.getRedisOperation();

    if (redisOperation != null) {
      redisOperation.del(i);
    }

    DbOperation dbOperation = operationCollections.getDbOperation();

    if (dbOperation != null) {
      editor = dbOperation.editor(i, t);
    }

    if (redisOperation != null) {
      redisOperation.insert(t);
    }

    return editor;
  }


  public boolean del(I i) {
    boolean del = false;

    OperationCollections operationCollections = this.operationCollection();

    RedisOperation redisOperation = operationCollections.getRedisOperation();
    if (redisOperation != null) {
      redisOperation.del(i);
    }

    DbOperation dbOperation = operationCollections.getDbOperation();
    if (dbOperation != null) {
      del = dbOperation.del(i);
    }

    return del;
  }

  public T byId(I i) {
    T result = null;
    RedisOperation redisOperation = this.operationCollection().getRedisOperation();
    if (redisOperation != null) {
      result = (T) redisOperation.byId(i);
    }

    DbOperation dbOperation = this.operationCollection().getDbOperation();

    if (dbOperation != null) {
      if (result == null) {
        System.out.println("从数据库获取");
        result = (T) dbOperation.byId(i);
      }
    }

    return result;
  }


  public OperationCollections operationCollection() {
    return byIdOperationFactory.factory(clazz());
  }

  protected Class<?> clazz() {
    throw new IllegalArgumentException("类型异常");
  }
}
