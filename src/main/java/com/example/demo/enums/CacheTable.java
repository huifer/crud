package com.example.demo.enums;

import com.example.demo.entity.ProjectInt;
import com.example.demo.entity.ProjectStr;

public enum CacheTable {
  PROJECT_STR("project_str", ProjectStr.class),
  PROJECT_INT("test:redis:project:int", ProjectInt.class),
  ;
  private final String name;
  private Class<?> clazz;

  CacheTable(String name, Class clazz) {
    this.name = name;
    this.clazz = clazz;
  }

  CacheTable(String name) {
    this.name = name;
  }

  public static String key(Class clazz) {
    String res = null;
    for (CacheTable value : CacheTable.values()) {
      if (value.clazz.equals(clazz)) {
        res = value.name;

      }
    }
    return res;
  }

  public String getName() {
    return name;
  }
}