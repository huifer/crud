package com.example.demo.service.project_str;

import com.example.demo.entity.ProjectStr;
import com.example.demo.service.facade.ByIdOperationFacade;
import com.example.demo.service.facade.CommonByIdOperation;
import com.example.demo.service.id.StrIdInterface;
import com.example.demo.service.operation.DbOperation;
import org.springframework.stereotype.Service;

@Service("ProjectStrFacade")
public class ProjectStrFacade extends CommonByIdOperation<ProjectStr, StrIdInterface> implements
    ByIdOperationFacade<ProjectStr, StrIdInterface> {

  @Override
  public boolean insert(ProjectStr projectInt) {

    return super.insert(projectInt);
  }

  @Override
  public ProjectStr byId(StrIdInterface strIdInterface) {
    return super.byId(strIdInterface);
  }

  @Override
  public boolean del(StrIdInterface strIdInterface) {
    return super.del(strIdInterface);
  }

  public boolean editor(StrIdInterface strIdInterface, ProjectStr projectInt) {
    return super.editor(strIdInterface, projectInt);
  }

  @Override
  public DbOperation getDbOperation() {
    return this.operationCollection().getDbOperation();
  }

  @Override
  protected Class<?> clazz() {
    return ProjectStr.class;
  }
}
