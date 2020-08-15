package com.example.webdemo;

import com.github.huifer.crud.beans.EnableCrudTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCrudTemplate
public class WebDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebDemoApplication.class, args);
  }

}
