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

  public static final String CACHE_PROJECT_STR = "cache:project_str";

  public void insert(ProjectStr projectStr) {
    super.insert(String.valueOf(projectStr.getId()), projectStr);
  }

  public void update(StrIdInterface strIdInterface, ProjectStr projectStr) {
    super.update(String.valueOf(strIdInterface.id()), projectStr);
  }

  public void del(StrIdInterface strIdInterface) {
    super.delete(String.valueOf(strIdInterface.id()));
  }

  public ProjectStr byId(StrIdInterface intIdInterface) {
    return super.byId(String.valueOf(intIdInterface.id()));
  }

  @Override
  public Class<?> type() {
    return ProjectStr.class;
  }

  @Override
  public String key() {
    return CACHE_PROJECT_STR;
  }
}
