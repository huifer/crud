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

package com.github.huifer.crud.common.conf.json.impl;

import com.github.huifer.crud.common.conf.json.GsonConfigSetting;
import com.github.huifer.crud.common.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service(Constant.GSON_SETTING_BEAN_NAME)
public class GsonConfigSettingImpl implements GsonConfigSetting {


  @Override
  public GsonBuilder gsonBuild() {
    return new GsonBuilder();
  }

  @Override
  public Gson gson() {
    return new Gson();
  }

}
