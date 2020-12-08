package com.github.huifer.crud.common.utils;

final public class ThreadLocalHolder {

  /**
   * 线程变量存储 class
   */
  private static final ThreadLocal<Class<?>> classThreadLocal = new ThreadLocal<>();


  public static Class<?> getClassThreadLocal() {
    return classThreadLocal.get();
  }

  public static void setClassThreadLocal(Class<?> classThreadLocal) {
    ThreadLocalHolder.classThreadLocal.set(classThreadLocal);
  }
}
