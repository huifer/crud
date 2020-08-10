package com.example.demo.controller;

import com.example.demo.entity.ProjectInt;
import com.example.demo.service.facade.ByIdOperationFacade;
import com.example.demo.service.id.IntIdInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

  @Autowired
  @Qualifier("projectIntFacade")
  private ByIdOperationFacade<ProjectInt, IntIdInterface> byIdOperationFacade;

  @GetMapping("/int_1")
  public void int1(){
    ProjectInt projectInt = new ProjectInt();
    projectInt.setName("JJJ");

    byIdOperationFacade.insert(projectInt);
  }
}
