package com.github.huifer.crud.common.daotype;

import java.util.Arrays;
import java.util.List;

/**
 * @see com.github.huifer.crud.common.beans.EnableCrudTemplate
 */
public class EnableCrudTemplateThreadLocal {

  private static final ThreadLocal<DaoType> daoTypeThreadLocal = new ThreadLocal<>();
  private static final ThreadLocal<List<String>> scanPackages = new ThreadLocal<>();


  private EnableCrudTemplateThreadLocal() throws IllegalAccessException {
    throw new IllegalAccessException("this is a utils !");
  }

  /**
   * get scan packages from thread local
   *
   * @return scan package
   */
  public static List<String> getPackages() {
    return scanPackages.get();
  }

  /**
   * setting scan packages
   *
   * @param packages packages
   */
  public static void setScanPackages(String[] packages) {
    scanPackages.set(Arrays.asList(packages));
  }

  /**
   * get dao type
   *
   * @return daoTYpe
   */
  public static DaoType getDaoType() {
    return daoTypeThreadLocal.get();
  }

  /**
   * setting dao type
   *
   * @param daoType dao type
   */
  public static void setDaoType(DaoType daoType) {
    DaoType cache = daoTypeThreadLocal.get();
    if (cache == null) {
      daoTypeThreadLocal.set(daoType);
    }
  }

}
