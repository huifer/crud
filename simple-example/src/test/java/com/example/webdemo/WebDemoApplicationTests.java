package com.example.webdemo;

import com.example.webdemo.entity.IssuesEntity;
import com.example.webdemo.mapper.IssuesMapper;
import com.github.huifer.crud.interfaces.id.IntIdInterface;
import com.github.huifer.crud.service.facade.CrudFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebDemoApplicationTests {

  @Autowired
  private CrudFacade<IssuesEntity, IntIdInterface<Integer>> issueCrud;


  @Autowired
  private IssuesMapper issuesMapper;

  @Test
  void contextLoads() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("test_issues");

    issuesMapper.insert(issuesEntity);
    issueCrud.insert(issuesEntity);
  }

}
