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

package com.github.huifer.crud.common.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.huifer.crud.common.conf.json.GsonConfigSetting;
import com.github.huifer.crud.common.conf.json.JackJsonConfigSetting;
import com.github.huifer.crud.common.model.enums.JsonEnums;
import com.github.huifer.crud.common.utils.Constant;
import com.github.huifer.crud.common.utils.EnableAttrManager;
import com.github.huifer.crud.common.utils.GsonSingleManager;
import com.github.huifer.crud.common.utils.JackJsonSingleManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class JsonRunner implements CommandLineRunner, Ordered {

  @Autowired
  private ApplicationContext context;

  @Override
  public void run(String... args) throws Exception {
    jsonConfig();
  }

  private void jsonConfig() {

    JsonEnums jsonEnums = EnableAttrManager.getJsonEnums();
    switch (jsonEnums) {
      case GSON:
        settingGson();
        break;
      case JACK_JSON:
        settingJackJson();
        break;
      default:
        throw new RuntimeException("json type is null");
    }


  }

  private void settingJackJson() {
    Map<String, JackJsonConfigSetting> beansOfType = context
        .getBeansOfType(JackJsonConfigSetting.class);
    if (beansOfType.size() == 1) {
      for (Entry<String, JackJsonConfigSetting> entry : beansOfType.entrySet()) {
        JackJsonConfigSetting v = entry.getValue();
        ObjectMapper objectMapper = v.setObjectMapper();
        JackJsonSingleManager.setObjectMapper(objectMapper);
      }
    }
    else {
      beansOfType.remove(Constant.JACK_SERIALIZATION_BEAN_NAME);
      JackJsonConfigSetting jackJsonConfigSetting = new ArrayList<>(beansOfType.values()).get(0);
      JackJsonSingleManager.setObjectMapper(jackJsonConfigSetting.setObjectMapper());
    }
  }

  private void settingGson() {
    Map<String, GsonConfigSetting> beansOfType = context.getBeansOfType(GsonConfigSetting.class);

    if (beansOfType.size() == 1) {
      for (Entry<String, GsonConfigSetting> entry : beansOfType.entrySet()) {
        GsonConfigSetting v = entry.getValue();
        if (settingGsonManager(v)) {
          break;
        }
      }
    }

    else {
      beansOfType.remove(Constant.GSON_SETTING_BEAN_NAME);
      settingGsonManager(new ArrayList<>(beansOfType.values()).get(0));
    }


  }

  private boolean settingGsonManager(GsonConfigSetting v) {
    GsonBuilder gsonBuilder = v.gsonBuild();
    if (!gsonBuilder.equals(GsonSingleManager.getGsonBuilder())) {
      GsonSingleManager.setGsonBuilder(gsonBuilder);
      Gson gson = gsonBuilder.create();
      GsonSingleManager.setGson(gson);
      return true;
    }

    Gson gson = v.gson();
    if (!gson.equals(GsonSingleManager.getGson())) {
      GsonSingleManager.setGson(gson);
    }
    return false;
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
