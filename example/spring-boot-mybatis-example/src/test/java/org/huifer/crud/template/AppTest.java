package org.huifer.crud.template;

import org.huifer.crud.template.mapper.IssuesMapper;
import org.huifer.crud.template.model.IssuesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppTest {

  @Autowired
  private IssuesMapper issuesMapper;

  @Test
  void main() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("asd");
    issuesMapper.insertSelective(issuesEntity);
  }
}