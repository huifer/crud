package com.github.huifer.jpa.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "issue", schema = "scrum", catalog = "")
public class IssueEntity {
  private long id;
  private String newTitle;
  private String context;
  private Timestamp startTime;
  private Timestamp endTime;
  private Timestamp estimateStartTime;
  private Timestamp estimateEndTime;
  private Long actualWorkTime;
  private String state;
  private String priority;
  private Long accUserId;
  private Long pid;
  private Timestamp createTime;
  private Long createUser;
  private Timestamp updateTime;
  private Long updateUser;
  private Long version;
  private Byte isDelete;
  private BigDecimal totalPrice;

  @Id
  @Column(name = "id")
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Basic
  @Column(name = "new_title")
  public String getNewTitle() {
    return newTitle;
  }

  public void setNewTitle(String newTitle) {
    this.newTitle = newTitle;
  }

  @Basic
  @Column(name = "context")
  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  @Basic
  @Column(name = "start_time")
  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  @Basic
  @Column(name = "end_time")
  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  @Basic
  @Column(name = "estimate_start_time")
  public Timestamp getEstimateStartTime() {
    return estimateStartTime;
  }

  public void setEstimateStartTime(Timestamp estimateStartTime) {
    this.estimateStartTime = estimateStartTime;
  }

  @Basic
  @Column(name = "estimate_end_time")
  public Timestamp getEstimateEndTime() {
    return estimateEndTime;
  }

  public void setEstimateEndTime(Timestamp estimateEndTime) {
    this.estimateEndTime = estimateEndTime;
  }

  @Basic
  @Column(name = "actual_work_time")
  public Long getActualWorkTime() {
    return actualWorkTime;
  }

  public void setActualWorkTime(Long actualWorkTime) {
    this.actualWorkTime = actualWorkTime;
  }

  @Basic
  @Column(name = "state")
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Basic
  @Column(name = "priority")
  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  @Basic
  @Column(name = "acc_user_id")
  public Long getAccUserId() {
    return accUserId;
  }

  public void setAccUserId(Long accUserId) {
    this.accUserId = accUserId;
  }

  @Basic
  @Column(name = "pid")
  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }

  @Basic
  @Column(name = "create_time")
  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  @Basic
  @Column(name = "create_user")
  public Long getCreateUser() {
    return createUser;
  }

  public void setCreateUser(Long createUser) {
    this.createUser = createUser;
  }

  @Basic
  @Column(name = "update_time")
  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  @Basic
  @Column(name = "update_user")
  public Long getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(Long updateUser) {
    this.updateUser = updateUser;
  }

  @Basic
  @Column(name = "version")
  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  @Basic
  @Column(name = "is_delete")
  public Byte getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

  @Basic
  @Column(name = "total_price")
  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IssueEntity that = (IssueEntity) o;
    return id == that.id &&
        Objects.equals(newTitle, that.newTitle) &&
        Objects.equals(context, that.context) &&
        Objects.equals(startTime, that.startTime) &&
        Objects.equals(endTime, that.endTime) &&
        Objects.equals(estimateStartTime, that.estimateStartTime) &&
        Objects.equals(estimateEndTime, that.estimateEndTime) &&
        Objects.equals(actualWorkTime, that.actualWorkTime) &&
        Objects.equals(state, that.state) &&
        Objects.equals(priority, that.priority) &&
        Objects.equals(accUserId, that.accUserId) &&
        Objects.equals(pid, that.pid) &&
        Objects.equals(createTime, that.createTime) &&
        Objects.equals(createUser, that.createUser) &&
        Objects.equals(updateTime, that.updateTime) &&
        Objects.equals(updateUser, that.updateUser) &&
        Objects.equals(version, that.version) &&
        Objects.equals(isDelete, that.isDelete) &&
        Objects.equals(totalPrice, that.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, newTitle, context, startTime, endTime, estimateStartTime, estimateEndTime, actualWorkTime, state, priority, accUserId, pid, createTime, createUser, updateTime, updateUser, version, isDelete, totalPrice);
  }
}
