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

package com.github.huifer.example.model;

import com.github.huifer.crud.common.annotation.ByIdEnhance;
import com.github.huifer.example.mapper.FirstModelMapper;
import com.github.huifer.example.mapper.SecondModelMapper;

public class TotalEnhance extends TotalModel {

  @ByIdEnhance(foreignKey = "firstId", mapper = FirstModelMapper.class, queryMethod = "selectById")
  private FirstModel firstModel;

  @ByIdEnhance(foreignKey = "secondId", mapper = SecondModelMapper.class, queryMethod = "findById")
  private SecondModel secondModel;

  public FirstModel getFirstModel() {
    return firstModel;
  }

  public void setFirstModel(FirstModel firstModel) {
    this.firstModel = firstModel;
  }

  public SecondModel getSecondModel() {
    return secondModel;
  }

  public void setSecondModel(SecondModel secondModel) {
    this.secondModel = secondModel;
  }
}
