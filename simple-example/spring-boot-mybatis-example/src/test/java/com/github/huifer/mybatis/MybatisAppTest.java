package com.github.huifer.mybatis;

import com.github.huifer.crud.common.intefaces.id.IntIdInterface;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.mybatis.entity.IssuesEntity;
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

    issuesEntity1.setNewTitle("update_test");

    crudFacade.editor(issuesEntity1);
  }
}