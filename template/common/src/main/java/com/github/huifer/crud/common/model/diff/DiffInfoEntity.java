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

package com.github.huifer.crud.common.model.diff;

public class DiffInfoEntity {

	/**
	 * filed name
	 */
	private String field;

	/**
	 * change message
	 */
	private String msg;

	/**
	 * tx id
	 */
	private String txId;

	/**
	 * old value
	 */
	private String ov;

	/***
	 * new value
	 *
	 * */
	private String nv;

	public String getOv() {
		return ov;
	}

	public void setOv(String ov) {
		this.ov = ov;
	}

	@Override
	public String toString() {
		return "{\"DiffInfoEntity\":{"
				+ "\"field\":\""
				+ field + '\"'
				+ ",\"msg\":\""
				+ msg + '\"'
				+ ",\"txId\":\""
				+ txId + '\"'
				+ ",\"ov\":\""
				+ ov + '\"'
				+ ",\"nv\":\""
				+ nv + '\"'
				+ "}}";

	}

	public String getNv() {
		return nv;
	}

	public void setNv(String nv) {
		this.nv = nv;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}
}