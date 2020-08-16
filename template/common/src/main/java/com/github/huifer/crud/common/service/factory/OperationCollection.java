package com.github.huifer.crud.common.service.factory;


import com.github.huifer.crud.common.intefaces.CrudTemplate;
import com.github.huifer.crud.common.intefaces.operation.RedisOperation;

public class OperationCollection {
  private CrudTemplate dbOperation;
  private RedisOperation redisOperation;

  public CrudTemplate getDbOperation() {
    return dbOperation;
  }

  public void setDbOperation(CrudTemplate dbOperation) {
    this.dbOperation = dbOperation;
  }

  public RedisOperation getRedisOperation() {
    return redisOperation;
  }

  public void setRedisOperation(RedisOperation redisOperation) {
    this.redisOperation = redisOperation;
  }

}
