package com.example.webdemo;

import com.example.webdemo.entity.IssuesEntity;
import org.huifer.crud.interfaces.CrudTemplate;
import org.huifer.crud.interfaces.id.IntIdInterface;
import org.huifer.crud.service.CrudHashTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebDemoApplicationTests {


  @Autowired
  private CrudTemplate<IssuesEntity, IntIdInterface<Integer>> issueCrud;

  @Test
  void contextLoads() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("asda");

    issueCrud.insert(issuesEntity);
  }

}
