package com.github.huifer.mp;

import com.github.huifer.mp.mapper.IssuesMapper;
import com.github.huifer.mp.model.IssuesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppTest {

  @Autowired
  private IssuesMapper issuesMapper;

  @org.junit.Test
  public void main() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("aaaaaa");
    issuesMapper.insert(issuesEntity);
  }
}