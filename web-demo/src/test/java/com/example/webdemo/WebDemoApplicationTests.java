package com.example.webdemo;

import com.example.demo.service.id.IntIdInterface;
import com.example.demo.service.template.CrudHashTemplate;
import com.example.webdemo.entity.IssuesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebDemoApplicationTests {


  @Autowired
  @Qualifier("crudHashTemplate")
  private CrudHashTemplate<IssuesEntity, IntIdInterface<Integer>> issueCrud;

  @Test
  void contextLoads() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("asda");

    issueCrud.insert(issuesEntity);
  }

}
