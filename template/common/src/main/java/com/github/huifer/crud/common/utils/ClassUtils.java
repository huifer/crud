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

package com.github.huifer.crud.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassUtils {
	private ClassUtils() {
		throw new IllegalStateException("util class");
	}

	public static List<Field> getAllFields(Class<?> input) {
		List<Field> fieldList = new ArrayList<>();
		while (input != null &&
				!input.getName().equalsIgnoreCase("java.lang.object")) {
			fieldList.addAll(Arrays.asList(input.getDeclaredFields()));
			input = input.getSuperclass();
		}
		return fieldList;
	}

	public static Object getFieldValue(Object t, Class<?> entityClass, String field)
			throws IllegalAccessException {
		List<Field> allFields = getAllFields(entityClass);
		Object result = null;
		for (Field allField : allFields) {
			allField.setAccessible(true);
			if (allField.getName().equals(field)) {
				result = allField.get(t);
			}
		}
		return result;
	}
}
