package com.github.huifer.crud.common.utils;

import java.util.Arrays;
import java.util.List;

public class DiffThreadLocalHelper {

  private static final ThreadLocal<List<String>> SCAN_PACKAGE = new ThreadLocal<>();
  private static final ThreadLocal<String> BY_ID_METHOD = new ThreadLocal<>();

  public static String getIdMethod() {
    return BY_ID_METHOD.get();
  }

  public static void setByIdMethod(String idMethod) {
    BY_ID_METHOD.set(idMethod);
  }

  public static List<String> getScan() {
    return SCAN_PACKAGE.get();
  }

  public static void setScan(String[] packages) {
    SCAN_PACKAGE.set(Arrays.asList(packages));
  }
}