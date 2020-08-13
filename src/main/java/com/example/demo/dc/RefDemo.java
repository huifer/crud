package com.example.demo.dc;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

interface Parent<T> {

  void clazz();
}

class Child<T> implements Parent<T> {

  @Override
  public void clazz() {
  }
}


public class RefDemo {

  public static void main(String[] args) {
    Parent<Double> doubleChild = new Child<>();
    List<Integer> list = new ArrayList<>();

    Type[] genericInterfaces = list.getClass().getGenericInterfaces();

    doubleChild.clazz();// 期望输出 Double.class
    Parent<BigDecimal> bigDecimalChild = new Child<>();
    bigDecimalChild.clazz(); // 期望输出 BigDecimal.class

  }
}
