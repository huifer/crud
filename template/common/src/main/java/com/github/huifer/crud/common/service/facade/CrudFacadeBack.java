///*
// *
// * Copyright 2020-2020 HuiFer All rights reserved.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// */
//
//package com.github.huifer.crud.common.service.facade;
//
//
//import com.github.huifer.crud.common.intefaces.BaseEntity;
//import com.github.huifer.crud.common.intefaces.CrudTemplate;
//import com.github.huifer.crud.common.intefaces.id.IdInterface;
//import com.github.huifer.crud.common.intefaces.id.StrIdInterface;
//import com.github.huifer.crud.common.intefaces.operation.DbOperation;
//import com.github.huifer.crud.common.intefaces.operation.RedisOperation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CrudFacadeBack
//		implements CrudTemplate {
//
//
//	private static final Logger log = LoggerFactory.getLogger(CrudFacadeBack.class);
//
//	@Autowired
//	@Qualifier("commonDbOperation")
//	private DbOperation dbOperation;
//
//	@Autowired
//	@Qualifier("crudHashTemplateForRedis")
//	private RedisOperation redisOperation;
//
//	public <T extends BaseEntity> boolean insert(T t) {
//		boolean insert = dbOperation.insert(t, t.getClass());
//		redisOperation.setClass(t.getClass());
//		if (insert) {
//			log.debug("开始进行redis插入");
//			redisOperation.insert(t, (StrIdInterface) () -> t.getId().toString());
//		}
//
//		return insert;
//	}
//
//	public <T extends BaseEntity> T byId(IdInterface<String > i, Class<?> c) {
//		redisOperation.setClass(c);
//
//		Object o = redisOperation.byId(i);
//		if (o != null) {
//			return (T) o;
//		}
//		// select by db
//		Object db = dbOperation.byId(i, c);
//		if (db != null) {
//
//			// insert redis
//			redisOperation.insert(db, (StrIdInterface) () -> i.id().toString());
//			return (T) db;
//		}
//
//		return null;
//	}
//
//	public boolean del(IdInterface i, Class<?> c) {
//
//		// remove redis value
//		redisOperation.setClass(c);
//		redisOperation.del(i);
//		return dbOperation.del(i);
//	}
//
//	public <T extends BaseEntity> boolean editor(T t) {
//		redisOperation.setClass(t.getClass());
//		redisOperation.del((StrIdInterface) () -> t.getId().toString());
//
//		boolean editor = dbOperation.editor(() -> t.getId(), t);
//		if (editor) {
//			redisOperation.insert(t, (StrIdInterface) () -> t.getId().toString());
//		}
//
//		return editor;
//	}
//}