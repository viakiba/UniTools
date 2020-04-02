package com.paste.cloud.dto;

import lombok.Data;

@Data
public class LogInDTO {

    private String email;

    /**
     * 如果是密码就传密码的hash值
     */
    private String pwd;

    /**
     * 是否是密码登录 默认为0 0：验证码登录 1：密码登录
     */
    private int pwdIs;

}
