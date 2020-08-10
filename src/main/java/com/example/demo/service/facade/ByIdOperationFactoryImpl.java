package com.example.demo.service.facade;

import com.example.demo.service.operation.DbOperation;
import com.example.demo.service.operation.RedisOperation;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("ByIdOperationFactoryImpl")
public class ByIdOperationFactoryImpl implements ByIdOperationFactory {

  static Map<Class, DbOperation> dbOperationMap = new HashMap<>();
  static Map<Class, RedisOperation> redisOperationHashMap = new HashMap<>();
  @Autowired
  private ApplicationContext context;

  @PostConstruct
  public void init() {
    Map<String, DbOperation> beansOfType = context.getBeansOfType(DbOperation.class);
    beansOfType.forEach(
        (k, v) -> {
          Class type = v.type();
          dbOperationMap.put(type, v);
        }
    );

    Map<String, RedisOperation> beansOfType1 = context.getBeansOfType(RedisOperation.class);
    beansOfType1.forEach((k, v) -> {
      Class type = v.type();
      redisOperationHashMap.put(type, v);
    });

  }

  @Override
  public OperationCollections factory(Class<?> clazz) {
    OperationCollections operationCollections = new OperationCollections();
    DbOperation dbOperation = dbOperationMap.get(clazz);
    RedisOperation redisOperation = redisOperationHashMap.get(clazz);

    operationCollections.setDbOperation(dbOperation);
    operationCollections.setRedisOperation(redisOperation);

    return operationCollections;
  }
}
