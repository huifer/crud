package com.github.huifer.crud.common.runner;

import com.github.huifer.crud.common.intefaces.A;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CrudTemplateRunner {

  public static final Map<Class<?>, MapperAndCacheInfo> mapperAndCacheInfoMap = new HashMap<>();


  public static A getA(Class<?> a) {
    return mapperAndCacheInfoMap.get(a).getA();
  }

  public static MapperAndCacheInfo getMapperAndCacheInfo(Class clazz) {
    return mapperAndCacheInfoMap.get(clazz);
  }


  protected void afterRunner() {

  }

}