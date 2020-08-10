package com.example.demo.service.project_str;

import com.example.demo.entity.ProjectInt;
import com.example.demo.entity.ProjectStr;
import com.example.demo.mapper.ProjectIntMapper;
import com.example.demo.mapper.ProjectStrMapper;
import com.example.demo.service.id.IntIdInterface;
import com.example.demo.service.id.StrIdInterface;
import com.example.demo.service.operation.DbOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProjectStrDbOperationImpl")
public class ProjectStrDbOperationImpl implements DbOperation<ProjectStr, StrIdInterface> {

  @Autowired
  private ProjectStrMapper projectStrMapper;

  @Override
  public boolean insert(ProjectStr projectInt) {
    return projectStrMapper.insert(projectInt) > 0;
  }

  @Override
  public ProjectStr byId(StrIdInterface interfaces) {
    return projectStrMapper.selectByPrimaryKey(interfaces.id());
  }

  @Override
  public boolean del(StrIdInterface interfaces) {
    return projectStrMapper.deleteByPrimaryKey(interfaces.id()) > 0;
  }

  @Override
  public boolean editor(StrIdInterface interfaces, ProjectStr projectInt) {
    // 更新存在策略
    ProjectStr projectInt1 = this.byId(interfaces);
    projectInt1.setName(projectInt.getName());

    return this.projectStrMapper.updateByPrimaryKey(projectInt1) > 0;
  }

  @Override
  public Class<?> type() {
    return ProjectStr.class;
  }
}
