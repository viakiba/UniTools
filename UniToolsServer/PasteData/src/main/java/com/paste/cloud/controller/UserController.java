package com.paste.cloud.controller;

import com.paste.cloud.compoent.Constants;
import com.paste.cloud.compoent.JSONResult;
import com.paste.cloud.compoent.MailUtil;
import com.paste.cloud.dto.LogInDTO;
import com.paste.cloud.dto.RegisterDTO;
import com.paste.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.paste.cloud.compoent.JSONResult.*;

/**
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailUtil mailUtil;
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final Pattern compile = Pattern.compile(REGEX_EMAIL);

    @PostMapping("/logIn")
    public JSONObject logIn(@RequestBody LogInDTO logInDTO) {
        return userService.logIn(logInDTO);
    }

    @PostMapping("/getCode")
    public JSONObject getVerifyCode(@RequestParam(defaultValue = "") String email){
        Matcher matcher = compile.matcher(email);
        boolean matches = matcher.matches();
        if(matches){
            try {
                mailUtil.sendVerifyCode(email);
            } catch (Exception e) {
                e.printStackTrace();
                return inner_error;
            }
        }else{
            return JSONResult.getResult(Constants.errorCode, Constants.email_verify_error);
        }
        return success_result;
    }

    @PostMapping("/register")
    public JSONObject register(@RequestBody @Valid RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

}
