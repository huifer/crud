package com.example.demo.mapper;

import com.example.demo.entity.ProjectInt;
import com.example.demo.service.runner.MapperRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProjectStrMapperTest {

  @Test
  void t() {
    A<Integer, ProjectInt> a = MapperRunner.getA(ProjectInt.class);
    ProjectInt projectInt = new ProjectInt();
    projectInt.setName("adasdasd");

    a.insert(projectInt);
  }

}