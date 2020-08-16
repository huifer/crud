package com.github.huifer.jpa;

import com.github.huifer.crud.common.beans.EnableCrudTemplate;
import com.github.huifer.crud.common.daotype.DaoType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCrudTemplate(daoType = DaoType.JPA)
public class JpaApp {
  public static void main(String[] args) {
    SpringApplication.run(JpaApp.class, args);
  }

}
