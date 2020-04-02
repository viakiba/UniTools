package com.paste.cloud.model;

import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class User {
    /**
     * 用户唯一ID
     */
    private String uid;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 注册时间
     */
    private Date registerTime;
}