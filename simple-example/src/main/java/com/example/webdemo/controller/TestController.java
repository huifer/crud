package com.example.webdemo.controller;

import com.example.webdemo.entity.IssuesEntity;
import com.github.huifer.crud.interfaces.id.IntIdInterface;
import com.github.huifer.crud.service.facade.CrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

  @Autowired
  private CrudFacade<IssuesEntity, IntIdInterface<Integer>> issueCrud;

  @GetMapping("/test")
  public Object test() {
    IssuesEntity issuesEntity = issueCrud.byId(new IntIdInterface<Integer>() {
      @Override
      public Integer id() {
        return 49;
      }
    }, IssuesEntity.class);
    return issuesEntity;
  }
}