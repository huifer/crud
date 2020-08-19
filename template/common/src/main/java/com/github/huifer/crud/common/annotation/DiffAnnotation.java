package com.github.huifer.crud.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DiffAnnotation {


  /**
   * 字段中文名称
   */
  String name() default "";

  /**
   * 消息,在这里使用[old]和[new]进行替换
   */
  String msg() default "";

  /**
   * mapper class
   */
  Class<?> mapper() default Object.class;

  /**
   * 链接对象
   */
  Class<?> outJoin() default Object.class;


  /**
   * 外联对象需要显示的字符串属性,用来展示的连接字段
   */
  String outField() default "";


}