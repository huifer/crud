package com.example.demo.service.project_str;

import com.example.demo.entity.ProjectStr;
import com.example.demo.service.id.StrIdInterface;
import com.example.demo.service.operation.RedisHashKeyOperation;
import com.example.demo.service.operation.RedisOperation;
import org.springframework.stereotype.Service;

@Service("ProjectStrRedisOperationImpl")
public class ProjectStrRedisOperationImpl
    extends RedisHashKeyOperation<ProjectStr>
    implements
    RedisOperation<ProjectStr, StrIdInterface> {

  public static final String CACHE_PROJECT_INT = "cache:project_int";

  public void insert(ProjectStr projectStr) {
    super.insert(CACHE_PROJECT_INT, String.valueOf(projectStr.getId()), projectStr);
  }

  public void update(StrIdInterface strIdInterface, ProjectStr projectStr) {
    super.update(CACHE_PROJECT_INT, String.valueOf(strIdInterface.id()), projectStr);
  }

  public void del(StrIdInterface strIdInterface) {
    super.delete(CACHE_PROJECT_INT, String.valueOf(strIdInterface.id()));
  }

  public ProjectStr byId(StrIdInterface intIdInterface) {
    return super.byId(CACHE_PROJECT_INT, String.valueOf(intIdInterface.id()), ProjectStr.class);

  }

  @Override
  public Class<?> type() {
    return ProjectStr.class;
  }
}
