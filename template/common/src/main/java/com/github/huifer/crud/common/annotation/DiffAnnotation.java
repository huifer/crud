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
   * @return 字段中文名称
   */
  String name() default "";

  /**
   * 消息,在这里使用[old]和[new]进行替换
   * @return 消息
   */
  String msg() default "";

  /**
   * mapper class
   * @return mapper.class
   */
  Class<?> mapper() default Object.class;

  /**
   * 链接对象
   * @return object
   */
  Class<?> outJoin() default Object.class;


  /**
   * 外联对象需要显示的字符串属性,用来展示的连接字段
   * @return out-field
   */
  String outField() default "";


}