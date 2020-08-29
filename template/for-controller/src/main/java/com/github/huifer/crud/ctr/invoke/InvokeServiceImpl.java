package com.github.huifer.crud.ctr.invoke;

import com.github.huifer.crud.common.intefaces.id.IdInterface;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.crud.ctr.entity.AbsEntity;
import com.github.huifer.crud.ctr.entity.ResultVO;
import com.google.gson.Gson;
import java.io.PrintWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvokeServiceImpl implements
    InvokeService {

  Gson gson = new Gson();
  @Autowired
  private CrudFacade crudFacade;

  @Override
  public void invoke(PrintWriter writer, String url, AbsEntity param, Class<?> resType,
      Class<?> idType) {
    if (crudFacade == null) {
      throw new RuntimeException("操作机为空");
    }
    boolean operation = false;
    Object returnString = null;
    if (url.endsWith("add")) {
      operation = crudFacade.insert(param);
    }
    else if (url.endsWith("/editor")) {
      operation = crudFacade.editor(param);

    }
    else if (url.endsWith("byId")) {
      Object o = crudFacade.byId(new IdInterface() {
        @Override
        public Object id() {
          if (idType.equals(Integer.class)) {

            return Integer.parseInt(String.valueOf(param.getId()));
          }
          else {
            return String.valueOf(param.getId());
          }
        }
      }, resType);
      if (o != null) {
        operation = true;
        returnString = o;

      }
    }
    else if (url.endsWith("/del")) {
      operation = crudFacade.del(new IdInterface() {
        @Override
        public Object id() {
          if (idType.equals(Integer.class)) {

            return Integer.parseInt(String.valueOf(param.getId()));
          }
          else {
            return String.valueOf(param.getId());
          }
        }
      }, resType);
    }

    ResultVO res = null;
    if (operation) {
      res = ResultVO.success(returnString);
    }
    else {
      res = ResultVO.failed();
    }
    writer.write(gson.toJson(res));
  }
}
