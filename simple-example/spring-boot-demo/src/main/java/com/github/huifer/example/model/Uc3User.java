package com.github.huifer.example.model;

import java.time.LocalDateTime;

import com.github.huifer.crud.common.intefaces.BaseEntity;

public class Uc3User implements BaseEntity {
    /**
    * ID
    */
    private Integer id;

    /**
    * 姓名
    */
    private String name;

    /**
    * 头像URL
    */
    private String avatar;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 手机(L)
    */
    private String mobile;

    /**
    * 身份证
    */
    private String idCard;

    /**
    * 登录帐号(L)
    */
    private String username;

    /**
    * 登录密码
    */
    private String password;

    /**
    * 用户类型
    */
    private String userType;

    /**
    * 人资ID
    */
    private String hrUserId;

    /**
    * 人资CODE
    */
    private String hrUserCode;

    /**
    * 部门ID
    */
    private Integer deptId;

    /**
    * 机构ID
    */
    private Integer companyId;

    /**
    * 岗位ID
    */
    private Integer postId;

    /**
    * 角色组ID
    */
    private Integer roleGroupId;

    /**
    * 职务
    */
    private String duty;

    /**
    * 备注说明
    */
    private String remark;

    /**
    * 上次登录时间
    */
    private LocalDateTime lastLoginTime;

    /**
    * 上次修改密码时间
    */
    private LocalDateTime lastChangePassword;

    /**
    * 数据来源(HR)
    */
    private String source;

    /**
    * 同步忽略(1忽略)
    */
    private Byte skipSync;

    /**
    * 版本
    */
    private Integer version;

    /**
    * 数据状态(0:离职;1在职)
    */
    private Byte status;

    /**
    * 删除标志(1删除)
    */
    private Byte deleted;

    /**
    * 创建用户
    */
    private Integer createUser;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新用户
    */
    private Integer updateUser;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getHrUserId() {
        return hrUserId;
    }

    public void setHrUserId(String hrUserId) {
        this.hrUserId = hrUserId;
    }

    public String getHrUserCode() {
        return hrUserCode;
    }

    public void setHrUserCode(String hrUserCode) {
        this.hrUserCode = hrUserCode;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getRoleGroupId() {
        return roleGroupId;
    }

    public void setRoleGroupId(Integer roleGroupId) {
        this.roleGroupId = roleGroupId;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getLastChangePassword() {
        return lastChangePassword;
    }

    public void setLastChangePassword(LocalDateTime lastChangePassword) {
        this.lastChangePassword = lastChangePassword;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Byte getSkipSync() {
        return skipSync;
    }

    public void setSkipSync(Byte skipSync) {
        this.skipSync = skipSync;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", avatar=").append(avatar);
        sb.append(", email=").append(email);
        sb.append(", mobile=").append(mobile);
        sb.append(", idCard=").append(idCard);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", userType=").append(userType);
        sb.append(", hrUserId=").append(hrUserId);
        sb.append(", hrUserCode=").append(hrUserCode);
        sb.append(", deptId=").append(deptId);
        sb.append(", companyId=").append(companyId);
        sb.append(", postId=").append(postId);
        sb.append(", roleGroupId=").append(roleGroupId);
        sb.append(", duty=").append(duty);
        sb.append(", remark=").append(remark);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", lastChangePassword=").append(lastChangePassword);
        sb.append(", source=").append(source);
        sb.append(", skipSync=").append(skipSync);
        sb.append(", version=").append(version);
        sb.append(", status=").append(status);
        sb.append(", deleted=").append(deleted);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}