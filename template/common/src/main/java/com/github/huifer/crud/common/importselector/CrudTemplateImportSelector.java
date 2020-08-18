package com.github.huifer.crud.common.importselector;

import com.github.huifer.crud.common.beans.EnableCrudTemplate;
import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.daotype.EnableCrudTemplateThreadLocal;
import java.util.Map;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class CrudTemplateImportSelector implements ImportSelector {

  public static final String DAO_TYPE = "daoType";

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
