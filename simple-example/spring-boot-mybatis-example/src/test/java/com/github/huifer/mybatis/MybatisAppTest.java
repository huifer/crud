package com.github.huifer.mybatis;

import com.github.huifer.crud.common.intefaces.id.IntIdInterface;
import com.github.huifer.crud.common.service.facade.CrudEntityFacade;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.mybatis.entity.IssuesEntity;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisAppTest {

  @Autowired
  private CrudFacade<IssuesEntity, IntIdInterface<Integer>> crudFacade;


  @Test
  void name() {
//    IssuesEntity issuesEntity = new IssuesEntity();
//    issuesEntity.setNewTitle("mybatis_test");
//    crudFacade.insert(issuesEntity);

    IssuesEntity issuesEntity1 = crudFacade.byId(new IntIdInterface<Integer>() {
      @Override
      public Integer id() {
        return 63;
      }
    }, IssuesEntity.class);
    System.out.println();
    issuesEntity1.setNewTitle("update_test321312");

    crudFacade.editor(issuesEntity1);
  }

  @Autowired
  private CrudEntityFacade<IssuesEntity> crudEntityFacade;

  @Test
  void testEntity() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("assda");

    crudEntityFacade.insert(issuesEntity);
  }
}