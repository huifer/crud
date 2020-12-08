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

package com.github.huifer.example;

import static com.github.huifer.crud.common.utils.Constant.CRUD_FACADE_BEAN_NAME;
import com.github.huifer.crud.common.service.facade.CrudEntityFacade;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.example.model.FirstModel;
import com.github.huifer.example.model.IssuesEntity;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisAppTest {

  Gson gson = new Gson();

  @Autowired
  @Qualifier(CRUD_FACADE_BEAN_NAME)
  private CrudFacade crudFacade;

  @Autowired
  private CrudEntityFacade crudEntityFacade;

  @Test
  void testInsert() throws InterruptedException {

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(System.currentTimeMillis());
        IssuesEntity issuesEntity = crudFacade.byId(106, IssuesEntity.class);
        System.out.println(gson.toJson(issuesEntity));
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(System.currentTimeMillis());
        FirstModel firstModel = crudFacade.byId(1, FirstModel.class);
        System.out.println(gson.toJson(firstModel));
      }
    });

    t1.start();
    t2.start();
    Thread.sleep(3000);
    System.out.println("exit");
  }

//  @Test
//  void testById() {
//    IssuesEntity issuesEntity66 = crudFacade.byId(new IntIdInterface() {
//      @Override
//      public Integer id() {
//        return 106;
//      }
//
//    }, IssuesEntity.class);
//    System.out.println();
//  }
//
//  @Test
//  void testEditor() {
//    IssuesEntity issuesEntity66 = crudFacade.byId(new IntIdInterface() {
//      @Override
//      public Integer id() {
//        return 106;
//      }
//
//    }, IssuesEntity.class);
//
//    issuesEntity66.setNewTitle("mybatis_editor_test");
//
//    crudFacade.editor(issuesEntity66);
//  }
//
//  @Test
//  void testEditor2() {
//    IssuesEntity issuesEntity66 = crudFacade.byId(new IntIdInterface() {
//      @Override
//      public Integer id() {
//        return 68;
//      }
//
//    }, IssuesEntity.class);
//
//    issuesEntity66.setNewTitle("mybatis_editor_test");
//
//    crudFacade.editor(issuesEntity66);
//  }
//
//  @Test
//  void testDel() {
//    crudFacade.del(new IntIdInterface() {
//      @Override
//      public Integer id() {
//        return 67;
//      }
//    }, IssuesEntity.class);
//  }
//
//  @Test
//  void name() {
//    IssuesEntity issuesEntity = new IssuesEntity();
//    issuesEntity.setNewTitle("mybatis_test");
//    crudFacade.insert(issuesEntity);
//
////    IssuesEntity issuesEntity1 = crudFacade.byId(new IntIdInterface<Integer>() {
////      @Override
////      public Integer id() {
////        return 63;
////      }
////    }, IssuesEntity.class);
////    System.out.println();
////    issuesEntity1.setNewTitle("update_test321312");
////
////    crudFacade.editor(issuesEntity1);
//  }
//
//  @Test
//  void testEntity() {
//    IssuesEntity issuesEntity = new IssuesEntity();
//    issuesEntity.setNewTitle("og");
//
//    crudEntityFacade.insert(issuesEntity);
//  }
//

}