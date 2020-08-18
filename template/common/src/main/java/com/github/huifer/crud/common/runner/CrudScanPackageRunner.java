package com.github.huifer.crud.common.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CrudScanPackageRunner implements CommandLineRunner, Ordered {


  @Override
  public void run(String... args) throws Exception {
    scanPackage();
  }

  private void scanPackage() {

  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}