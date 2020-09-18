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

import com.github.huifer.crud.common.service.facade.CrudEntityFacade;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.example.model.IssuesEntity;
import com.github.huifer.example.model.Uc3User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisAppTest {

	@Autowired
	private CrudFacade crudFacade;

	@Autowired
	private CrudEntityFacade<IssuesEntity> crudEntityFacade;

	@Test
	void testInsert() {
//    Uc3User issuesEntity = new Uc3User();
//    issuesEntity.setName("mybatis_test");
//    crudFacade.insert(issuesEntity);


		Uc3User uc3User = crudFacade.byId(1749, Uc3User.class);
		uc3User.setAvatar("asfasfafs");
		crudFacade.editor(uc3User);
		System.out.println();
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