package com.github.huifer.crud.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provided for interface use and class use
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CacheKey {

  String key();

  Class<?> type();

  String idFiled() default "";


  /**
   * id 生成函数
   */
  String idMethod() default "";
}
