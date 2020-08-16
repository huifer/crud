package com.github.huifer.mybatis.plus.mybatis;

import com.github.huifer.crud.common.intefaces.id.IntIdInterface;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.mybatis.plus.mybatis.entity.IssuesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusAppTest {

  @Autowired
  private CrudFacade<IssuesEntity, IntIdInterface<Integer>> crudFacade;


  @Test
  void name() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("mybatis_plus");
    crudFacade.insert(issuesEntity);
  }
}