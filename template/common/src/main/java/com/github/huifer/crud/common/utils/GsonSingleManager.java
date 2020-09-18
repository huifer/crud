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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSingleManager {

	private static Gson gson = new Gson();

	private static GsonBuilder gsonBuilder = new GsonBuilder();

	private GsonSingleManager() {

	}

	public static GsonBuilder getGsonBuilder() {
		return gsonBuilder;
	}

	public static void setGsonBuilder(GsonBuilder gsonBuilder) {
		GsonSingleManager.gsonBuilder = gsonBuilder;
	}

	public static Gson getGson() {
		return gson;
	}

	public static void setGson(Gson gson) {
		GsonSingleManager.gson = gson;
	}
}
