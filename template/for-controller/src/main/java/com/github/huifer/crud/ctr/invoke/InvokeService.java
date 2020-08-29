package com.github.huifer.crud.ctr.invoke;

import com.github.huifer.crud.ctr.entity.AbsEntity;
import java.io.PrintWriter;

public interface InvokeService {

  void invoke(PrintWriter writer, String url, AbsEntity param, Class<?> resType,
      Class<?> idType);
}
