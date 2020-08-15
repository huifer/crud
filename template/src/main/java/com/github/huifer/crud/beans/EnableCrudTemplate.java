package com.github.huifer.crud.beans;

import com.github.huifer.crud.daotype.DaoType;
import com.github.huifer.crud.importselector.CrudTemplateImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {Beans.class, CrudTemplateImportSelector.class})
public @interface EnableCrudTemplate {

    DaoType daoType() default DaoType.MYBATIS;
}