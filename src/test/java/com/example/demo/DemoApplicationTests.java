package com.example.demo;

import com.example.demo.entity.ProjectInt;
import com.example.demo.mapper.ProjectStrMapper;
import com.example.demo.service.facade.ByIdOperationFacade;
import com.example.demo.service.id.IntIdInterface;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class DemoApplicationTests {

  Gson gson = new Gson();
  @Autowired
  private ProjectStrMapper projectStrMapper;
  @Autowired
  @Qualifier("projectIntFacade")
  private ByIdOperationFacade<ProjectInt, IntIdInterface> byIdOperationFacade;
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Test
  void testInsert() {
    ProjectInt projectInt = new ProjectInt();
    projectInt.setName("ADAAD");
    this.byIdOperationFacade.insert(projectInt);
  }

  @Test
  void testUpdate() {
    ProjectInt projectInt = this.byIdOperationFacade.byId(new IntIdInterface() {
      @Override
      public Integer id() {
        return 1;
      }
    });

    projectInt.setName("update");
    this.byIdOperationFacade.editor(new IntIdInterface() {
      @Override
      public Integer id() {
        return projectInt.getId();
      }
    }, projectInt);
  }

  @Test
  void testDel() {
    this.byIdOperationFacade.del(new IntIdInterface() {
      @Override
      public Integer id() {
        return 1;
      }
    });
  }

  @Test
  void testById() {
    ProjectInt projectInt = this.byIdOperationFacade.byId(new IntIdInterface() {
      @Override
      public Integer id() {
        return 1;
      }
    });
    System.out.println();
  }
}
