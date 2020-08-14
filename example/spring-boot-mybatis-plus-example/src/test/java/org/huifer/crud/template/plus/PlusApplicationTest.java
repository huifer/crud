package org.huifer.crud.template.plus;

import static org.junit.jupiter.api.Assertions.*;

import org.huifer.crud.interfaces.id.IdInterface;
import org.huifer.crud.service.CrudHashTemplate;
import org.huifer.crud.template.plus.model.IssuesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlusApplicationTest {

  @Autowired
  private CrudHashTemplate<IssuesEntity, IdInterface<Integer>> crud;

  @Test
  public void test() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("aasfasfasfasfasfasfs");

    crud.insert(issuesEntity);
  }
}