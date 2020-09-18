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

package com.github.huifer.crud.common.conf.json.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.huifer.crud.common.conf.json.JackJsonConfigSetting;
import com.github.huifer.crud.common.utils.Constant;

import org.springframework.stereotype.Service;

@Service(Constant.JACK_SERIALIZATION_BEAN_NAME)
public class JackJsonConfigSettingImpl implements
		JackJsonConfigSetting {

	@Override
	public ObjectMapper setObjectMapper() {
		return new ObjectMapper();
	}
}
