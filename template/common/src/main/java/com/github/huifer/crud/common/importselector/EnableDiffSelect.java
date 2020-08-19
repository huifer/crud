package com.github.huifer.crud.common.importselector;

import com.github.huifer.crud.common.beans.EnableDiff;
import com.github.huifer.crud.common.utils.DiffThreadLocalHelper;
import java.util.Map;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class EnableDiffSelect implements ImportSelector {

  @Override
  public String[] selectImports(
      AnnotationMetadata annotationMetadata) {

    Map<String, Object> annotationAttributes = annotationMetadata
        .getAnnotationAttributes(EnableDiff.class.getName());

    String[] scanPackages = (String[]) annotationAttributes.get("scanPackages");
    String byIdSQL = (String) annotationAttributes.get("byIdMethod");
    DiffThreadLocalHelper.setScan(scanPackages);
    DiffThreadLocalHelper.setByIdMethod(byIdSQL);
    return new String[0];
  }
}