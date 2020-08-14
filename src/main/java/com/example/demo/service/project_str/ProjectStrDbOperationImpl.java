package com.example.demo.service.project_str;

import com.example.demo.entity.ProjectStr;
import com.example.demo.mapper.A;
import com.example.demo.service.id.StrIdInterface;
import com.example.demo.service.operation.DbOperation;
import com.example.demo.service.runner.MapperRunner;
import org.springframework.stereotype.Service;

@Service("ProjectStrDbOperationImpl")
public class ProjectStrDbOperationImpl implements DbOperation<ProjectStr, StrIdInterface<String>> {

  private A<String, ProjectStr> getA() {
    A<String, ProjectStr> a = MapperRunner.getA(ProjectStr.class);
    return a;
  }

  @Override
  public boolean insert(ProjectStr projectInt) {
    return getA().insertSelective(projectInt) > 0;
  }

  @Override
  public ProjectStr byId(StrIdInterface<String> interfaces) {
    return getA().selectByPrimaryKey(interfaces.id());
  }

  @Override
  public boolean del(StrIdInterface<String> interfaces) {
    return getA().deleteByPrimaryKey(interfaces.id()) > 0;
  }

  @Override
  public boolean editor(StrIdInterface<String> interfaces, ProjectStr projectInt) {
    // 更新存在策略
    ProjectStr projectInt1 = this.byId(interfaces);
    projectInt1.setName(projectInt.getName());

    return this.getA().updateByPrimaryKeySelective(projectInt1) > 0;
  }

  @Override
  public Class<?> type() {
    return ProjectStr.class;
  }
}
