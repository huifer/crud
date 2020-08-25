package com.example.webdemo.crud;

import com.example.webdemo.entity.IssuesEntity;
import com.example.webdemo.mapper.IssuesMapper;
import com.github.huifer.crud.interfaces.id.IntIdInterface;
import com.github.huifer.crud.service.facade.CrudFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestCrudFacade {

  @Autowired
  private CrudFacade<IssuesEntity, IntIdInterface<Integer>> issueCrud;


  @Autowired
  private IssuesMapper issuesMapper;

  @Test
  void contextLoads() {

    IssuesEntity issuesEntity = issueCrud.byId(new IntIdInterface<Integer>() {
      @Override
      public Integer id() {
        return 49;
      }
    }, IssuesEntity.class);

    issuesEntity.setNewTitle("asdasdasdsd");
    issueCrud.editor(new IntIdInterface<Integer>() {
      @Override
      public Integer id() {
        return 49;
      }
    }, issuesEntity);
  }

}
