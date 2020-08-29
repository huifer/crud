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

package com.github.huifer.crud.ctr.runner;

import com.github.huifer.crud.common.runner.ScanUtils;
import com.github.huifer.crud.ctr.annotation.CrudController;
import com.github.huifer.crud.ctr.annotation.entity.CrudControllerEntity;
import com.github.huifer.crud.ctr.utils.EnableCrudControllerAttr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CrudControllerRunner implements Ordered, CommandLineRunner {

  public static final String ADD = "/add";
  public static final String DEL = "/del";
  public static final String EDITOR = "/editor";
  public static final String BY_ID = "/byId";
  public static final String[] uriList = {ADD, DEL, EDITOR, BY_ID};
  public static final String PRE = "/rest";
  private static final List<CrudControllerEntity> crudControllerEntities = new ArrayList<>();
  private static final Map<String, CrudControllerEntity> crudControllerEntityMap = new HashMap<>();

  public static CrudControllerEntity get(String uri) {

    return crudControllerEntityMap.get(uri);
  }

  public static List<CrudControllerEntity> getCrudControllerEntities() {
    return crudControllerEntities;
  }

  public static Map<String, CrudControllerEntity> getCrudControllerEntityMap() {
    return crudControllerEntityMap;
  }

  public void run(String... args) throws Exception {
    scan();
  }

  private void scan() {
    String[] scanPackageCrud = EnableCrudControllerAttr.getScanPackageCrud();

    if (scanPackageCrud != null) {
      for (String pck : scanPackageCrud) {

        Set<Class<?>> classes = ScanUtils.getClasses(pck);
        for (Class<?> aClass : classes) {
          CrudController annotation = aClass.getAnnotation(CrudController.class);
          if (annotation != null) {
            String uri = annotation.uri();
            if (!StringUtils.isEmpty(uri)) {
              for (String s : uriList) {
                String url = PRE + uri + s;
                CrudControllerEntity crudControllerEntity = new CrudControllerEntity();
                crudControllerEntity.setUri(url);
                crudControllerEntity.setType(aClass);
                crudControllerEntity.setIdType(annotation.idType());
                setCommonValue(url, crudControllerEntity);
              }
            }
          }
        }

      }
    }

  }

  private void setCommonValue(String uri, CrudControllerEntity crudControllerEntity) {
    crudControllerEntities.add(crudControllerEntity);
    crudControllerEntityMap.put(uri, crudControllerEntity);
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }

}
