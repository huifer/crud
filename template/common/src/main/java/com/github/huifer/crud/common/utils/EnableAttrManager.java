package com.github.huifer.crud.common.utils;

import com.github.huifer.crud.common.model.enums.JsonEnums;

public class EnableAttrManager {

  private static JsonEnums jsonEnums = null;
  private static String[] scanPackageDao = null;
  private static String byIdMethod = null;
  private static String[] scanPackageDiff = null;

  private static String selectByIdMethodName = null;
  private static String deleteByIdMethodName = null;
  private static String updateByIdMethodName = null;
  private static String insertMethodName = null;

  private EnableAttrManager() {

  }

  public static String getSelectByIdMethodName() {
    return selectByIdMethodName;
  }

  public static void setSelectByIdMethodName(String selectByIdMethodName) {
    EnableAttrManager.selectByIdMethodName = selectByIdMethodName;
  }

  public static String getDeleteByIdMethodName() {
    return deleteByIdMethodName;
  }

  public static void setDeleteByIdMethodName(String deleteByIdMethodName) {
    EnableAttrManager.deleteByIdMethodName = deleteByIdMethodName;
  }

  public static String getUpdateByIdMethodName() {
    return updateByIdMethodName;
  }

  public static void setUpdateByIdMethodName(String updateByIdMethodName) {
    EnableAttrManager.updateByIdMethodName = updateByIdMethodName;
  }

  public static String getInsertMethodName() {
    return insertMethodName;
  }

  public static void setInsertMethodName(String insertMethodName) {
    EnableAttrManager.insertMethodName = insertMethodName;
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


}
