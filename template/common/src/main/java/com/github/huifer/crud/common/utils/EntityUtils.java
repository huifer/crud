package com.github.huifer.crud.common.utils;

import java.lang.reflect.Field;

public class EntityUtils {

  public static Object getFiled(Object o, String filed) {
    Class<?> aClass = o.getClass();
    Field[] fields = aClass.getDeclaredFields();
    Object res = null;
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true);
      if (fields[i].getName().equals(filed)) {
        try {
          res = fields[i].get(o);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    return res;
  }
}