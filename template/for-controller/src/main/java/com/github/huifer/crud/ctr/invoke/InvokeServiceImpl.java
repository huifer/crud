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

package com.github.huifer.crud.ctr.invoke;

import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.serialize.SerializationCall;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.crud.ctr.entity.AbsEntity;
import com.github.huifer.crud.ctr.entity.OpEnums;
import com.github.huifer.crud.ctr.entity.ResultVO;
import com.github.huifer.crud.ctr.validated.ValidatedScanService;
import java.io.PrintWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvokeServiceImpl implements
    InvokeService {

  @Autowired
  private CrudFacade crudFacade;
  @Autowired
  private ValidatedScanService validatedScanService;

  @Autowired
  private SerializationCall serializationCall;


  private static String[] splitByDotForId(String s2) {
    String s = s2;
    return s.split("\\.");
  }

  @Override
  public void invoke(PrintWriter writer, String url, AbsEntity param, Class<?> paramType,
      Class<?> idType) throws Exception {
    if (crudFacade == null) {
      throw new RuntimeException("curd facade is null .");
    }
    //  validated com.github.huifer.crud.ctr.validated.ValidatedInterface
    OpEnums opEnums = conv(url);
    validatedScanService.invoke(param, paramType, opEnums);
    boolean operation = false;
    Object returnString = null;
    if (url.endsWith("add")) {
      operation = crudFacade.insert(param);
    }
    else if (url.endsWith("editor")) {
      setParamId(param, idType);

      operation = crudFacade.editor(param);

    }
    else if (url.endsWith("byId")) {
      Object o = crudFacade.byId(new IdInterface() {
        @Override
        public Object id() {
          if (idType.equals(Integer.class)) {
            String[] split = splitByDotForId(String.valueOf(param.getId()));
            return Integer.parseInt(split[0]);
          }
          else {
            return String.valueOf(param.getId());
          }
        }
      }, paramType);
      if (o != null) {
        operation = true;
        returnString = o;

      }
    }
    else if (url.endsWith("del")) {
      operation = crudFacade.del(new IdInterface() {
        @Override
        public Object id() {
          if (idType.equals(Integer.class)) {
            String[] split = splitByDotForId(String.valueOf(param.getId()));
            return Integer.parseInt(split[0]);
          }
          else {
            return String.valueOf(param.getId());
          }
        }
      }, paramType);
    }

    ResultVO res = null;
    if (operation) {
      res = ResultVO.success(returnString);
    }
    else {
      res = ResultVO.failed();
    }
    writer.write(toJson(res));
  }

  private void setParamId(AbsEntity param, Class<?> idType) {
    if (idType.equals(Integer.class)) {

      String[] split = splitByDotForId(String.valueOf(param.getId()));
      param.setId(split[0]);
    }
  }

  private String toJson(ResultVO res) {
    return serializationCall.toJson(res);
  }

  private OpEnums conv(String url) {
    if (url.endsWith("add")) {
      return OpEnums.ADD;
    }
    else if (url.endsWith("editor")) {
      return OpEnums.EDITOR;

    }
    else if (url.endsWith("byId")) {
      return OpEnums.BY_ID;

    }
    else if (url.endsWith("del")) {
      return OpEnums.DELETE;

    }
    return null;
  }
}
