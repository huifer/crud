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
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * byId enhance annotation
 *
 * @see com.github.huifer.crud.common.annotation.entity.ByIdEnhanceEntity
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ByIdEnhance {

	/**
	 * foreign key filed name
	 *
	 * @return foreign key filed name
	 */
	String foreignKey();

	/**
	 * mapper query name
	 *
	 * @return queryById  method name
	 */
	String queryMethod();

	/**
	 * mapper
	 *
	 * @return mapper class
	 */
	Class<?> mapper();

	/**
	 * foreign key type
	 *
	 * @return foreign key type
	 */
	Class<?> idType() default Integer.class;
}
