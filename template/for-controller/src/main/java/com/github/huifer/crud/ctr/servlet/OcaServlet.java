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

package com.github.huifer.crud.ctr.servlet;

import com.github.huifer.crud.common.serialize.SerializationCall;
import com.github.huifer.crud.common.utils.Constant;
import com.github.huifer.crud.ctr.annotation.entity.CrudControllerEntity;
import com.github.huifer.crud.ctr.entity.AbsEntity;
import com.github.huifer.crud.ctr.entity.ResultVO;
import com.github.huifer.crud.ctr.invoke.ApplicationContextProvider;
import com.github.huifer.crud.ctr.invoke.InvokeService;
import com.github.huifer.crud.ctr.runner.CrudControllerRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class OcaServlet extends HttpServlet {


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws  IOException {
    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    PrintWriter writer = resp.getWriter();

    String wholeStr = getBody(req);
    String requestURI = req.getRequestURI();

    Object requestBody = null;
    Class<?> paramType = null;

    CrudControllerEntity crudControllerEntity = CrudControllerRunner.get(requestURI);
    if (crudControllerEntity == null) {
      writer.write(toJson(ResultVO.failed()));
    }
    paramType = crudControllerEntity.getType();

    requestBody = fromJson(wholeStr, paramType);
    Class<?> idType = crudControllerEntity.getIdType();

    ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
    InvokeService bean = applicationContext.getBean(InvokeService.class);
    try {
      bean.invoke(writer, requestURI, (AbsEntity) requestBody, paramType, idType);
    } catch (Exception e) {
      writer.write(toJson(ResultVO.failed(e.getMessage())));
    }

  }

  protected Object fromJson(String wholeStr, Class<?> resType) {
    ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
    SerializationCall serializationCall = applicationContext.getBean(SerializationCall.class,
        Constant.SERIALIZATION_CALL_IMPL);
    return serializationCall.fromJson(wholeStr, resType);
  }

  protected String toJson(ResultVO failed) {
    ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
    SerializationCall serializationCall = applicationContext
        .getBean(SerializationCall.class, Constant.SERIALIZATION_CALL_IMPL);
    return serializationCall.toJson(failed);
  }

  protected String getBody(HttpServletRequest req) throws IOException {
    BufferedReader br = req.getReader();

    String str, wholeStr = "";
    while ((str = br.readLine()) != null) {

      wholeStr += str;
    }
    return wholeStr;
  }

}
