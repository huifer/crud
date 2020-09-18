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

package com.github.huifer.crud.common.runner;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.huifer.crud.common.annotation.DiffAnnotation;
import com.github.huifer.crud.common.annotation.HavingDiff;
import com.github.huifer.crud.common.annotation.entity.DiffAnnotationEntity;
import com.github.huifer.crud.common.utils.EnableAttrManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class DiffRunner implements CommandLineRunner, Ordered {


	/**
	 * key: entity class  value: Map -> key: filed ,value: {@link DiffAnnotationEntity}
	 */
	static Map<Class<?>, Map<String, DiffAnnotationEntity>> cache = new HashMap<>();

	public static Map<String, DiffAnnotationEntity> get(Class<?> clazz) {
		return cache.get(clazz);
	}

	@Override
	public void run(String... args) throws Exception {
		String[] scanPackageDiff = EnableAttrManager.getScanPackageDiff();
		if (scanPackageDiff != null) {

			List<String> scan = Arrays.asList(scanPackageDiff.clone());
			if (!CollectionUtils.isEmpty(scan)) {
				for (String packageStr : scan) {
					if (!StringUtils.isEmpty(packageStr)) {
						Set<Class<?>> classes = ScanUtils.getClasses(packageStr);
						for (Class<?> aClass : classes) {

							Map<String, DiffAnnotationEntity> diffEntityMap = clazzWork(aClass);
							if (!CollectionUtils.isEmpty(diffEntityMap)) {

								cache.put(aClass, diffEntityMap);
							}
						}
					}
				}
			}
		}
	}

	private Map<String, DiffAnnotationEntity> clazzWork(Class<?> clazz) {
		HavingDiff havingDiff = clazz.getAnnotation(HavingDiff.class);
		Map<String, DiffAnnotationEntity> map = new HashMap<>();
		// has havingDiff
		if (havingDiff != null) {

			for (Field declaredField : clazz.getDeclaredFields()) {
				declaredField.setAccessible(true);
				// filed name
				String fieldName = declaredField.getName();
				// get diffAnnotation
				DiffAnnotation diffAnnotation = declaredField.getAnnotation(DiffAnnotation.class);
				if (diffAnnotation != null) {
					DiffAnnotationEntity diffAnnotationEntity = annToEntity(diffAnnotation);
					map.put(fieldName, diffAnnotationEntity);
				}
			}
		}
		return map;
	}


	/**
	 * annotation to entity
	 */
	private DiffAnnotationEntity annToEntity(DiffAnnotation diffAnnotation) {

		DiffAnnotationEntity diffAnnotationEntity = new DiffAnnotationEntity();
		diffAnnotationEntity.setName(diffAnnotation.name());
		diffAnnotationEntity.setMsg(diffAnnotation.msg());
		diffAnnotationEntity.setMapper(diffAnnotation.mapper());
		diffAnnotationEntity.setOutJoin(diffAnnotation.outJoin());
		diffAnnotationEntity.setOutField(diffAnnotation.outField());

		return diffAnnotationEntity;

	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}