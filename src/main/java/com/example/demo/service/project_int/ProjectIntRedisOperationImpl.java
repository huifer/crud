package com.example.demo.service.project_int;

import com.example.demo.entity.ProjectInt;
import com.example.demo.service.id.IntIdInterface;
import com.example.demo.service.operation.RedisHashKeyOperation;
import com.example.demo.service.operation.RedisOperation;
import org.springframework.stereotype.Service;

@Service("ProjectIntRedisOperationImpl")
public class ProjectIntRedisOperationImpl
    extends RedisHashKeyOperation<ProjectInt>
    implements
    RedisOperation<ProjectInt, IntIdInterface> {

  public static final String CACHE_PROJECT_INT = "cache:project_int";

  public void insert(ProjectInt projectInt) {
    super.insert(String.valueOf(projectInt.getId()), projectInt);
  }

  public void update(IntIdInterface strIdInterface, ProjectInt projectInt) {
    super.update(String.valueOf(strIdInterface.id()), projectInt);
  }

  public void del(IntIdInterface strIdInterface) {
    super.delete(String.valueOf(strIdInterface.id()));
  }

  public ProjectInt byId(IntIdInterface intIdInterface) {
    return super.byId(String.valueOf(intIdInterface.id()));
  }

  @Override
  public Class<?> type() {
    return ProjectInt.class;
  }

  @Override
  public String key() {
    return CACHE_PROJECT_INT;
  }
}
