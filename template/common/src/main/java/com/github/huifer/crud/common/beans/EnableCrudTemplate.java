package com.github.huifer.crud.common.beans;

import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.importselector.CrudTemplateImportSelector;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {Beans.class, CrudTemplateImportSelector.class})
public @interface EnableCrudTemplate {

  DaoType daoType() default DaoType.MYBATIS;

  String[] scanPackages() default {};

}