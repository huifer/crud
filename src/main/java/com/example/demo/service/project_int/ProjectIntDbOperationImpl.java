package com.example.demo.service.project_int;

import com.example.demo.entity.ProjectInt;
import com.example.demo.mapper.A;
import com.example.demo.service.id.IntIdInterface;
import com.example.demo.service.operation.CommonDbOperation;
import com.example.demo.service.operation.DbOperation;
import org.springframework.stereotype.Service;

@Service("ProjectIntDbOperationImpl")
public class ProjectIntDbOperationImpl extends
    CommonDbOperation<ProjectInt, IntIdInterface<Integer>> implements
    DbOperation<ProjectInt, IntIdInterface<Integer>> {


  @Override
  public boolean insert(ProjectInt projectInt) {
    return super.insert(projectInt);
  }

  @Override
  public ProjectInt byId(IntIdInterface<Integer> interfaces) {
    A<Integer, ProjectInt> a = getA();

    return a.selectByPrimaryKey(interfaces.id());
  }

  @Override
  protected A<Integer, ProjectInt> getA() {
    return super.getA();
  }

  @Override
  public boolean del(IntIdInterface<Integer> interfaces) {
    return getA().deleteByPrimaryKey(interfaces.id()) > 0;
  }

  @Override
  public boolean editor(IntIdInterface<Integer> interfaces, ProjectInt projectInt) {
    // 更新存在策略
    ProjectInt projectInt1 = this.byId(interfaces);
    projectInt1.setName(projectInt.getName());

    return this.getA().updateByPrimaryKey(projectInt1) > 0;
  }

  @Override
  public Class<?> type() {
    return ProjectInt.class;
  }
}
