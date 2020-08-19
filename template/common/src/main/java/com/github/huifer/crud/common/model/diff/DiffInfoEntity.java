package com.github.huifer.crud.common.model.diff;

public class DiffInfoEntity {

  private String field;
  private String msg;
  private String txId;
  private String ov;
  private String nv;

  public String getOv() {
    return ov;
  }

  public void setOv(String ov) {
    this.ov = ov;
  }

  public String getNv() {
    return nv;
  }

  public void setNv(String nv) {
    this.nv = nv;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getTxId() {
    return txId;
  }

  public void setTxId(String txId) {
    this.txId = txId;
  }
}