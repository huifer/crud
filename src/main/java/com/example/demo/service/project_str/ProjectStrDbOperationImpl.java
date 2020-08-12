package com.example.demo.service.project_str;

import com.example.demo.entity.ProjectStr;
import com.example.demo.mapper.A;
import com.example.demo.service.id.StrIdInterface;
import com.example.demo.service.operation.DbOperation;
import com.example.demo.service.runner.MapperRunner;
import org.springframework.stereotype.Service;

@Service("ProjectStrDbOperationImpl")
public class ProjectStrDbOperationImpl implements DbOperation<ProjectStr, StrIdInterface> {

  private A<String, ProjectStr> getA() {
    A<String, ProjectStr> a = MapperRunner.getA(ProjectStr.class);
    return a;
  }

  @Override
  public boolean insert(ProjectStr projectInt) {
    return getA().insert(projectInt) > 0;
  }

  @Override
  public ProjectStr byId(StrIdInterface interfaces) {
    return getA().selectByPrimaryKey((String) interfaces.id());
  }

  @Override
  public boolean del(StrIdInterface interfaces) {
    return getA().deleteByPrimaryKey((String) interfaces.id()) > 0;
  }

  @Override
  public boolean editor(StrIdInterface interfaces, ProjectStr projectInt) {
    // 更新存在策略
    ProjectStr projectInt1 = this.byId(interfaces);
    projectInt1.setName(projectInt.getName());

    return this.getA().updateByPrimaryKey(projectInt1) > 0;
  }

  @Override
  public Class<?> type() {
    return ProjectStr.class;
  }
}
