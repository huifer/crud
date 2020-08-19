package com.github.huifer.crud.common.beans;


import com.github.huifer.crud.common.importselector.EnableDiffSelect;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {EnableDiffSelect.class})
public @interface EnableDiff {

  String[] scanPackages() default {};

  String byIdMethod() default "selectById";
}
