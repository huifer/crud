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

package com.github.huifer.crud.ctr.importselector;

import com.github.huifer.crud.ctr.beans.EnableCrudController;
import com.github.huifer.crud.ctr.utils.EnableCrudControllerAttr;
import java.util.Map;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class EnableCrudControllerSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata annotationMetadata) {
    Map<String, Object> annotationAttributes = annotationMetadata
        .getAnnotationAttributes(EnableCrudController.class.getName());
    String[] scanPackages = (String[]) annotationAttributes.get("scanPackages");
    EnableCrudControllerAttr.setScanPackageCrud(scanPackages);
    return new String[0];
  }
}
