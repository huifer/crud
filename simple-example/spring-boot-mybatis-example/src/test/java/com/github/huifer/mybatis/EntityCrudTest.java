package com.github.huifer.mybatis;

import com.github.huifer.crud.common.intefaces.id.StrIdInterface;
import com.github.huifer.crud.common.service.facade.CrudEntityFacade;
import com.github.huifer.mybatis.entity.IssuesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityCrudTest {

  @Autowired
  private CrudEntityFacade<IssuesEntity> crudEntityFacade;

  @Test
  void testInsert() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("insert");
    crudEntityFacade.insert(issuesEntity);
  }


  @Test
  void testById() {
    IssuesEntity issuesEntity = crudEntityFacade.byId(new StrIdInterface<String>() {
      @Override
      public String id() {
        return "insert";
      }
    }, IssuesEntity.class);
    System.out.println();
  }

  @Test
  void testUpdate() {
    IssuesEntity issuesEntity = crudEntityFacade.byId(new StrIdInterface<String>() {
      @Override
      public String id() {
        return "insert";
      }
    }, IssuesEntity.class);

    issuesEntity.setId(999);
    crudEntityFacade.editor(issuesEntity);
  }

  @Test
  void testDel() {
    crudEntityFacade.del(new StrIdInterface<String>() {
      @Override
      public String id() {
        return "insert";
      }
    }, IssuesEntity.class);
  }
}