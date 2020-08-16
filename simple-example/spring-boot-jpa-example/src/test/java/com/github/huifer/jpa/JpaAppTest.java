package com.github.huifer.jpa;

import com.github.huifer.jpa.entity.IssuesEntity;
import com.github.huifer.jpa.repo.IssueRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaAppTest {
    @Autowired
    IssueRepo repo;

    @Test
    void name() {
        IssuesEntity issuesEntity = new IssuesEntity();
        issuesEntity.setNewTitle("jpa_test");
        repo.save(issuesEntity);
    }
}