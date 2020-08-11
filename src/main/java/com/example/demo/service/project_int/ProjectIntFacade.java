package com.example.demo.service.project_int;

import com.example.demo.entity.ProjectInt;
import com.example.demo.service.facade.ByIdOperationFacade;
import com.example.demo.service.facade.CommonByIdOperation;
import com.example.demo.service.id.IntIdInterface;
import com.example.demo.service.operation.DbOperation;
import org.springframework.stereotype.Service;

@Service("projectIntFacade")
public class ProjectIntFacade extends CommonByIdOperation<ProjectInt, IntIdInterface> implements
    ByIdOperationFacade<ProjectInt, IntIdInterface> {

  @Override
  public boolean insert(ProjectInt projectInt) {
    return super.insert(projectInt);
  }

  @Override
  public ProjectInt byId(IntIdInterface intIdInterface) {
    return super.byId(intIdInterface);
  }

  @Override
  public boolean del(IntIdInterface intIdInterface) {
    return super.del(intIdInterface);
  }

  public boolean editor(IntIdInterface intIdInterface, ProjectInt projectInt) {
    return super.editor(intIdInterface, projectInt);
  }

  @Override
  public DbOperation getDbOperation() {
    return this.operationCollection().getDbOperation();
  }

  @Override
  protected Class<?> clazz() {
    return ProjectInt.class;
  }
}
