package com.manjushirwa.pojo.admin.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.manjushirwa.pojo.base.DataEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 后台管理用户表
 *
 * @author Ciaoyen
 * @since 2018/01/02
 */
@Data
@TableName("sys_user")
public class User extends DataEntity<User> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Office company;    // 归属公司
    @TableField(exist = false)
    private Office office;    // 归属部门
    @TableField(value = "LOGIN_NAME")
    private String loginName;// 登录名
    private String password;// 密码
    private String no;        // 工号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;    // 姓名
    private String email;    // 邮箱
    private String phone;    // 电话
    private String mobile;    // 手机
    @TableField(exist = false)
    private String userType;// 用户类型
    @TableField(exist = false)
    private String loginIp;    // 最后登陆IP
    @TableField(exist = false)
    private Date loginDate;    // 最后登陆日期
    @TableField(exist = false)
    private String loginFlag;    // 是否允许登陆
    @TableField(exist = false)
    private String photo;    // 头像
    @TableField(exist = false)
    private String oldLoginName;// 原登录名
    @TableField(exist = false)
    private String newPassword;    // 新密码
    @TableField(exist = false)
    private String oldLoginIp;    // 上次登陆IP
    @TableField(exist = false)
    private Date oldLoginDate;    // 上次登陆日期

    @TableField(exist = false)
    private Role role;    // 根据角色查询用户条件
    @TableField(exist = false)
    private List<Role> roles;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }

    private String Salt;    //密码盐

    public User(String id) {
        super.id = id;
    }

    public User() {

    }

    /**
     * 密码盐
     */
    public String getCredentialsSalt() {
        return getLoginName() + getSalt();
    }

    @Override
    protected Serializable pkVal() {
        return super.id;
    }

    public String getPassword() {
        return password;
    }
}
