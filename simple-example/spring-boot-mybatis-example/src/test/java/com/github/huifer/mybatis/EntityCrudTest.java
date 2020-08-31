/*
 *
 * Copyright 2020-2020 HuiFer All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.huifer.mybatis;

import com.github.huifer.crud.common.intefaces.id.StrIdInterface;
import com.github.huifer.crud.common.service.facade.CrudEntityFacade;
import com.github.huifer.mybatis.entity.IssuesEntity;
import java.util.Date;
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
    issuesEntity.setDate(new Date());
    crudEntityFacade.insert(issuesEntity);
  }


  @Test
  void testById() {
    IssuesEntity issuesEntity = crudEntityFacade.byId(new StrIdInterface<String>() {
      @Override
      public String id() {
        return "OOOinsert";
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