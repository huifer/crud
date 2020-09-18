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

import com.github.huifer.crud.common.intefaces.id.IntIdInterface;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.example.model.Uc3User;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Abc {

	@Autowired
	private CrudFacade<Uc3User, IntIdInterface> crudFacade;
	@Test
	public void tt() {
		Uc3User uc3User = new Uc3User();
		uc3User.setName("aaaaaaaaaaaaaaaa");
		crudFacade.insert(uc3User);
	}
}
