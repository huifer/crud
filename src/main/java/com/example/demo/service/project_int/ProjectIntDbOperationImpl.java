package com.example.demo.service.project_int;

import com.example.demo.entity.ProjectInt;
import com.example.demo.mapper.ProjectIntMapper;
import com.example.demo.service.id.IntIdInterface;
import com.example.demo.service.operation.DbOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProjectIntDbOperationImpl")
public class ProjectIntDbOperationImpl implements DbOperation<ProjectInt, IntIdInterface> {

  @Autowired
  private ProjectIntMapper projectIntMapper;

  @Override
  public boolean insert(ProjectInt projectInt) {
    return projectIntMapper.insert(projectInt) > 0;
  }

  @Override
  public ProjectInt byId(IntIdInterface interfaces) {
    return projectIntMapper.selectByPrimaryKey(interfaces.id());
  }

  @Override
  public boolean del(IntIdInterface interfaces) {
    return projectIntMapper.deleteByPrimaryKey(interfaces.id()) > 0;
  }

  @Override
  public boolean editor(IntIdInterface interfaces, ProjectInt projectInt) {
    // 更新存在策略
    ProjectInt projectInt1 = this.byId(interfaces);
    projectInt1.setName(projectInt.getName());

    return this.projectIntMapper.updateByPrimaryKey(projectInt1) > 0;
  }

  @Override
  public Class<?> type() {
    return ProjectInt.class;
  }
}
