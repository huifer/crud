package com.example.demo.service.facade;

import com.example.demo.service.operation.DbOperation;
import com.example.demo.service.operation.RedisOperation;

public class OperationCollections {

  private DbOperation dbOperation;

  private RedisOperation redisOperation;

  public DbOperation getDbOperation() {
    return dbOperation;
  }

  public void setDbOperation(DbOperation dbOperation) {
    this.dbOperation = dbOperation;
  }

  public RedisOperation getRedisOperation() {
    return redisOperation;
  }

  public void setRedisOperation(RedisOperation redisOperation) {
    this.redisOperation = redisOperation;
  }
}
