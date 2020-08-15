package com.example.webdemo;

import com.example.webdemo.entity.IssuesEntity;
import com.github.huifer.crud.beans.EnableCrudTemplate;
import com.github.huifer.crud.interfaces.CrudTemplate;
import com.github.huifer.crud.interfaces.id.IntIdInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableCrudTemplate
class WebDemoApplicationTests {


    @Autowired
    private CrudTemplate<IssuesEntity, IntIdInterface<Integer>> issueCrud;

    @Test
    void contextLoads() {
        IssuesEntity issuesEntity = new IssuesEntity();
        issuesEntity.setNewTitle("test_issues");

        issueCrud.insert(issuesEntity);
    }

}
