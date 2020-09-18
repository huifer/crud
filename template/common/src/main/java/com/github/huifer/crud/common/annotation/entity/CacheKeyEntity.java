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

package com.github.huifer.crud.common.annotation.entity;


import com.github.huifer.crud.common.annotation.CacheKey;

/***
 * cache key annotation entity
 * @see com.github.huifer.crud.common.annotation.CacheKey
 */
public class CacheKeyEntity {

	/**
	 * redis-hash-key
	 */
	private String key;

	/**
	 * entity class
	 */
	private Class<?> type;

	/**
	 * id filed name (form entity )
	 */
	private String idFiled;

	/**
	 * id generator method name (form entity)
	 */
	private String idMethod;

	public CacheKeyEntity() {
	}

	public CacheKeyEntity(CacheKey cacheKey) {
		this.idFiled = cacheKey.idFiled();
		this.idMethod = cacheKey.idMethod();
		this.key = cacheKey.key();
		this.type = cacheKey.type();

	}

	public String getIdMethod() {
		return idMethod;
	}

	public void setIdMethod(String idMethod) {
		this.idMethod = idMethod;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getIdFiled() {
		return idFiled;
	}

	public void setIdFiled(String idFiled) {
		this.idFiled = idFiled;
	}
}