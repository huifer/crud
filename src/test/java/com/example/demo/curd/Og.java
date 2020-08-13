package com.example.demo.curd;

import com.example.demo.entity.ProjectInt;
import com.example.demo.service.id.IdInterface;
import com.example.demo.service.template.CrudHashTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Og {

  @Autowired
  CrudHashTemplate<ProjectInt, IdInterface<Integer>> crudHashTemplate;

  @Test
  void tt() {
    ProjectInt projectInt = new ProjectInt();
    projectInt.setName("asd");
    this.crudHashTemplate.insert(projectInt);
    ProjectInt projectInt1 = this.crudHashTemplate.byId(new IdInterface<Integer>() {
      @Override
      public Integer id() {
        return projectInt.getId();
      }
    }, ProjectInt.class);

    projectInt1.setName("t2123123123");

    this.crudHashTemplate.editor(new IdInterface<Integer>() {
      @Override
      public Integer id() {
        return projectInt1.getId();
      }
    }, projectInt1);

//    this.crudHashTemplate.del(new IdInterface<Integer>() {
//      @Override
//      public Integer id() {
//        return projectInt1.getId();
//      }
//    }, ProjectInt.class);
  }


}