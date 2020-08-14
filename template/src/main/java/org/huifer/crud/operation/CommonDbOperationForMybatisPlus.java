//package org.huifer.crud.operation;
//
//import org.huifer.crud.interfaces.A;
//import org.huifer.crud.interfaces.id.IdInterface;
//import org.huifer.crud.runner.MapperAndCacheInfo;
//import org.huifer.crud.runner.MapperRunner;
//
//public class CommonDbOperationForMybatisPlus<T, I extends IdInterface> {
//
//  Class<?> type;
//
//
//  public A getA() {
//    return MapperRunner.getA(type());
//  }
//
//  public MapperAndCacheInfo getMapper() {
//    return MapperRunner.mapperAndCacheInfoMap.get(type());
//  }
//
//  public boolean insert(T o) {
//    this.type = o.getClass();
//    boolean plus = getMapper().isPlus();
//    return getA().insertSelective(o) > 0;
//  }
//
//  public T byId(I idInterface, Class c) {
//    this.type = c;
//    boolean plus = getMapper().isPlus();
//
//    return (T) getA().selectByPrimaryKey(idInterface.id());
//  }
//
//  public boolean del(I id, Class c) {
//    this.type = c;
//
//    return getA().deleteByPrimaryKey(id.id()) > 0;
//  }
//
//  public boolean update(T t) {
//    this.type = t.getClass();
//
//    return getA().updateByPrimaryKeySelective(t) > 0;
//  }
//
//  public Class type() {
//    return this.type;
//  }
//}