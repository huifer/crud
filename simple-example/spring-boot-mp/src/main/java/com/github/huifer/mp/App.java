package com.github.huifer.mp;

import com.github.huifer.crud.common.beans.EnableCrudTemplate;
import com.github.huifer.crud.common.service.facade.CrudFacade;
import com.github.huifer.mp.mapper.IssuesMapper;
import com.github.huifer.mp.model.IssuesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/go")
@EnableCrudTemplate(
    scanPackages = {
        "com.github.huifer.mybatis"},
    selectByIdMethodName = "selectById",
    deleteByIdMethodName = "deleteById",
    updateByIdMethodName = "updateById",
    insertMethodName = "insert")
public class App {

  @Autowired
  private IssuesMapper mapper;
  @Autowired
  private CrudFacade crudFacade;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @GetMapping("/do")
  public void go() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("111111");
    IssuesEntity baseEntity = crudFacade.byId(4, IssuesEntity.class);
    baseEntity.setNewTitle("aaaa");
    crudFacade.editor(baseEntity);
    System.out.println();

  }
}
