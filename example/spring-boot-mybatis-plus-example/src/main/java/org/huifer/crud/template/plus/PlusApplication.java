package org.huifer.crud.template.plus;


import java.util.Collection;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.huifer.crud.beans.EnableCrudTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCrudTemplate
public class PlusApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext run = SpringApplication.run(PlusApplication.class, args);
    SqlSession bean = run.getBean(SqlSession.class);
    Configuration configuration = bean.getConfiguration();
    MapperRegistry mapperRegistry = configuration.getMapperRegistry();
    Collection<Class<?>> mappers = mapperRegistry.getMappers();
    System.out.println();
  }
}