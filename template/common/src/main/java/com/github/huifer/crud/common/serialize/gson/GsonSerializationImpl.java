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

package com.github.huifer.crud.common.serialize.gson;

import com.github.huifer.crud.common.model.enums.JsonEnums;
import com.github.huifer.crud.common.serialize.Serialization;
import com.github.huifer.crud.common.utils.GsonSingleManager;
import com.google.gson.Gson;

import org.springframework.stereotype.Service;

import static com.github.huifer.crud.common.utils.Constant.GSON_SERIALIZATION_BEAN_NAME;

@Service(GSON_SERIALIZATION_BEAN_NAME)
public class GsonSerializationImpl implements Serialization {


	@Override
	public String serialize(Object object) {
		Gson gson = GsonSingleManager.getGson();
		if (gson == null) {
			throw new RuntimeException("gson is null");
		}
		return gson.toJson(object);
	}

	@Override
	public JsonEnums jsonType() {
		return JsonEnums.GSON;
	}

	@Override
	public Object deserialize(String json, Class<?> type) {
		Gson gson = GsonSingleManager.getGson();
		return gson.fromJson(json, type);
	}
}
