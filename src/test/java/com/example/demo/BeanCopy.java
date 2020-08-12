package com.example.demo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

public class BeanCopy {

  public static void main(String[] args) {
    StrId strId = new StrId();
    strId.setId("str_id");

    CpId cpId = new CpId();
    BeanUtils.copyProperties(strId, cpId);
    System.out.println(cpId.getId());
  }
  @Data
  static class CpId {
    private Object id;

    public Object getId() {
      return id;
    }

    public void setId(Object id) {
      this.id = id;
    }
  }

  @Data
  static class StrId {

    private String id;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
  }
}
