package com.github.huifer.crud.ctr.servlet;

import com.github.huifer.crud.common.serialize.SerializationCall;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class OcaServlet extends HttpServlet {


  @Autowired
  private SerializationCall serializationCall;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    PrintWriter writer = resp.getWriter();

    String wholeStr = getBody(req);
    String requestURI = req.getRequestURI();
    System.out.println("当前请求地址" + requestURI);

    Object requestBody = null;
    Class<?> resType = null;

    CrudControllerEntity crudControllerEntity = CrudControllerRunner.get(requestURI);
    if (crudControllerEntity == null) {
      writer.write(toJson(ResultVO.failed()));
    }
    resType = crudControllerEntity.getType();

    requestBody = fromJson(wholeStr, resType);
    Class<?> idType = crudControllerEntity.getIdType();

    ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
    InvokeService bean = applicationContext.getBean(InvokeService.class);
    try {
      bean.invoke(writer, requestURI, (AbsEntity) requestBody, resType, idType);
    } catch (Exception e) {
      writer.write(toJson(ResultVO.failed(e.getMessage())));
    }

  }

  private Object fromJson(String wholeStr, Class<?> resType) {
    return serializationCall.fromJson(wholeStr, resType);
  }

  private String toJson(ResultVO failed) {
    return serializationCall.toJson(failed);
  }

  private String getBody(HttpServletRequest req) throws IOException {
    BufferedReader br = req.getReader();

    String str, wholeStr = "";
    while ((str = br.readLine()) != null) {

      wholeStr += str;
    }
    return wholeStr;
  }

}
