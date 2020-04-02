package com.paste.cloud.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterDTO {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String code;
    /**
     * 传密码的hash256的值
     */
    @NotBlank
    private String password;

}
