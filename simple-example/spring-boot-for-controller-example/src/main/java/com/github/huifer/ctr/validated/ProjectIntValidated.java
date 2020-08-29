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

package com.github.huifer.ctr.validated;

import com.github.huifer.crud.ctr.validated.ValidatedInterface;
import com.github.huifer.ctr.entity.ProjectInt;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class ProjectIntValidated implements ValidatedInterface<ProjectInt> {

  Gson gson = new Gson();


  public Class<?> entityClass() {
    return ProjectInt.class;
  }

  public void validateDelete(ProjectInt projectInt) {
    System.out.println(gson.toJson(projectInt));
  }

  public void validateAdd(ProjectInt projectInt) {
    System.out.println(gson.toJson(projectInt));
  }

  public void validateById(ProjectInt projectInt) {
    System.out.println(gson.toJson(projectInt));
  }

  public void validateEditor(ProjectInt projectInt) {
    System.out.println(gson.toJson(projectInt));
  }
}
