package com.paste.cloud.service;

import com.github.pagehelper.PageInfo;
import com.paste.cloud.dto.LogInDTO;
import com.paste.cloud.dto.RegisterDTO;
import com.paste.cloud.model.User;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 *
 */
public interface UserService {



    /**
     * 注册新用户
     * @param registerDTO
     * @return JSONObject
     */
    JSONObject register(RegisterDTO registerDTO);

    /**
     * 登录
     * @param logInDTO
     * @return
     */
    JSONObject logIn(LogInDTO logInDTO);
}