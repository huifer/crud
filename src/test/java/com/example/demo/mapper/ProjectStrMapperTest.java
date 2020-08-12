package com.example.demo.mapper;

import com.example.demo.entity.ProjectInt;
import com.example.demo.service.id.IntIdInterface;
import com.example.demo.service.operation.CommonDbOperation;
import com.example.demo.service.runner.MapperRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProjectStrMapperTest {

  @Autowired
  private CommonDbOperation<ProjectInt, IntIdInterface<Integer>> commonDbOperation;

  @Test
  void t() {
//    A<Integer, ProjectInt> a = MapperRunner.getA(ProjectInt.class);
    ProjectInt projectInt = new ProjectInt();
    projectInt.setName("od");
//
//    a.insert(projectInt);

    commonDbOperation.insert(projectInt);

  }

}