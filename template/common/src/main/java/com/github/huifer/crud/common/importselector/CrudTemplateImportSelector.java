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

package com.github.huifer.crud.common.importselector;

import com.github.huifer.crud.common.beans.EnableCrudTemplate;
import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.daotype.EnableCrudTemplateThreadLocal;
import java.util.Map;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class CrudTemplateImportSelector implements ImportSelector {


  @Override
  public String[] selectImports(AnnotationMetadata annotationMetadata) {
    // read attributes for EnableCrudTemplate
    Map<String, Object> annotationAttributes = annotationMetadata
        .getAnnotationAttributes(EnableCrudTemplate.class.getName());
    DaoType daoType = (DaoType) annotationAttributes.get("daoType");
    if (daoType == null) {
      throw new NullPointerException("dao_type property cannot be empty!");
    }
    // setting dao type
    EnableCrudTemplateThreadLocal.setDaoType(daoType);
    String[] scanPackages = (String[]) annotationAttributes.get("scanPackages");
    EnableCrudTemplateThreadLocal.setScanPackages(scanPackages);

    return new String[0];
  }
}
