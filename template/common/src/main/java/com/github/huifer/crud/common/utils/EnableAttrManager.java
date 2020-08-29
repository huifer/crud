package com.github.huifer.crud.common.utils;

import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.common.model.enums.JsonEnums;

public class EnableAttrManager {

  private static JsonEnums jsonEnums = null;
  private static DaoType daoType = null;
  private static String[] scanPackageDao = null;
  private static String byIdMethod = null;
  private static String[] scanPackageDiff = null;

  private EnableAttrManager() {

  }

  public static JsonEnums getJsonEnums() {
    return jsonEnums;
  }

  public static void setJsonEnums(JsonEnums jsonEnums) {
    EnableAttrManager.jsonEnums = jsonEnums;
  }

  public static String[] getScanPackageDao() {
    return scanPackageDao;
  }

  public static void setScanPackageDao(String[] scanPackageDao) {
    EnableAttrManager.scanPackageDao = scanPackageDao;
  }

  public static String getByIdMethod() {
    return byIdMethod;
  }

  public static void setByIdMethod(String byIdMethod) {
    EnableAttrManager.byIdMethod = byIdMethod;
  }

  public static String[] getScanPackageDiff() {
    return scanPackageDiff;
  }

  public static void setScanPackageDiff(String[] scanPackageDiff) {
    EnableAttrManager.scanPackageDiff = scanPackageDiff;
  }

  public static DaoType getDaoType() {
    return daoType;
  }


  public static void setDaoType(DaoType daoType) {
    EnableAttrManager.daoType = daoType;
  }


}
