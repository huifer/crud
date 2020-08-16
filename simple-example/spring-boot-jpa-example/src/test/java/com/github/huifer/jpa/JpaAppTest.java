package com.github.huifer.jpa;

import com.github.huifer.jpa.entity.IssueEntity;
import com.github.huifer.jpa.repo.IssueRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaAppTest {
  @Autowired
  @Qualifier("issueRepo")
  IssueRepo repo;

  @Test
  void name() {
    IssueEntity issuesEntity = new IssueEntity();
    issuesEntity.setNewTitle("jpa_test");
    repo.save(issuesEntity);
  }
}