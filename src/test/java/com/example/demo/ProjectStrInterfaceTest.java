package com.example.demo;

import com.example.demo.entity.ProjectInt;
import com.example.demo.entity.ProjectStr;
import com.example.demo.mapper.ProjectIntMapper;
import com.example.demo.service.facade.ByIdOperationFacade;
import com.example.demo.service.id.StrIdInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectStrInterfaceTest {

  @Autowired
  @Qualifier("ProjectStrFacade")
  private ByIdOperationFacade<ProjectStr, StrIdInterface> projectStrStrIdInterfaceByIdOperationFacade;

  @Autowired
  private ProjectIntMapper projectIntMapper;

  @Test
  void name() {
    ProjectInt project = new ProjectInt();
    project.setName("acacacaacaccaacascascascasca");

    projectIntMapper.insert(project);
  }

  @Test
  void testInsert() {

    ProjectStr project = new ProjectStr();
    project.setId("a4");
    project.setName("JJJ");
    this.projectStrStrIdInterfaceByIdOperationFacade.insert(project);
  }


}
