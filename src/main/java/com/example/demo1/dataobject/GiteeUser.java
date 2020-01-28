package com.example.demo1.dataobject;

import com.example.demo1.validator.emailcode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @date 2020/01/22
 */
public class GiteeUser implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空哦~")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码也不能为空哦！")
    private String password;
    @emailcode

    private String emailcode;
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailcode() {
        return emailcode;
    }

    public void setEmailcode(String emailcode) {
        this.emailcode = emailcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
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
}