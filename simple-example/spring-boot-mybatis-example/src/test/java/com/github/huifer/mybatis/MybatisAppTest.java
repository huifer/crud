package com.github.huifer.mybatis;

import com.github.huifer.crud.common.intefaces.id.IntIdInterface;
import com.github.huifer.crud.common.service.facade.CrudEntityFacade;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.mybatis.entity.IssuesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisAppTest {

  @Autowired
  private CrudFacade<IssuesEntity, IntIdInterface<Integer>> crudFacade;
  @Autowired
  private CrudEntityFacade<IssuesEntity> crudEntityFacade;

  @Test
  void testInsert() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("mybatis_test");
    crudFacade.insert(issuesEntity);
  }

  @Test
  void testById() {
    IssuesEntity issuesEntity66 = crudFacade.byId(new IntIdInterface<Integer>() {
      @Override
      public Integer id() {
        return 66;
      }

    }, IssuesEntity.class);

    IssuesEntity issuesEntity67 = crudFacade.byId(new IntIdInterface<Integer>() {
      @Override
      public Integer id() {
        return 67;
      }

    }, IssuesEntity.class);
  }

  @Test
  void testEditor() {
    IssuesEntity issuesEntity66 = crudFacade.byId(new IntIdInterface<Integer>() {
      @Override
      public Integer id() {
        return 66;
      }

    }, IssuesEntity.class);

    issuesEntity66.setNewTitle("mybatis_editor_test");

    crudFacade.editor(issuesEntity66);
  }

  @Test
  void testEditor2() {
    IssuesEntity issuesEntity66 = crudFacade.byId(new IntIdInterface<Integer>() {
      @Override
      public Integer id() {
        return 68;
      }

    }, IssuesEntity.class);

    issuesEntity66.setNewTitle("mybatis_editor_test");

    crudFacade.editor(issuesEntity66);
  }

  @Test
  void testDel() {
    crudFacade.del(new IntIdInterface<Integer>() {
      @Override
      public Integer id() {
        return 67;
      }
    }, IssuesEntity.class);
  }

  @Test
  void name() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("mybatis_test");
    crudFacade.insert(issuesEntity);

//    IssuesEntity issuesEntity1 = crudFacade.byId(new IntIdInterface<Integer>() {
//      @Override
//      public Integer id() {
//        return 63;
//      }
//    }, IssuesEntity.class);
//    System.out.println();
//    issuesEntity1.setNewTitle("update_test321312");
//
//    crudFacade.editor(issuesEntity1);
  }

  @Test
  void testEntity() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("og");

    crudEntityFacade.insert(issuesEntity);
  }
}