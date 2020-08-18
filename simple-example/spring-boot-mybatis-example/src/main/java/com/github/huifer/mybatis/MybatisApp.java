package com.github.huifer.mybatis;

import com.github.huifer.crud.common.beans.EnableCrudTemplate;
import com.github.huifer.crud.common.daotype.DaoType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCrudTemplate(daoType = DaoType.MYBATIS)
public class MybatisApp {

  public static void main(String[] args) {
    SpringApplication.run(MybatisApp.class, args);
  }
}
