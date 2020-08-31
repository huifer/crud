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

package com.github.huifer.ctr.rt;

import com.github.huifer.crud.common.model.enums.JsonEnums;
import com.github.huifer.crud.common.serialize.Serialization;
import com.github.huifer.crud.common.serialize.SerializationFactory;
import com.github.huifer.ctr.entity.ProjectInt;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RunTest {

  @Autowired
  private ApplicationContext context;
  @Autowired
  private SerializationFactory serializationFactory;


  @Test
  public void hh() {
    Map<String, Serialization> beansOfType = context.getBeansOfType(Serialization.class);

    Serialization factory = serializationFactory.factory(JsonEnums.GSON);
    System.out.println();
  }


  @Test
  public void gsonString() {
    ProjectInt projectInt = new ProjectInt();
    projectInt.setName("ad");
    System.out.println(serializationFactory.factory(JsonEnums.GSON).serialize(projectInt));
  }


}
