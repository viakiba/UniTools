package com.paste.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.paste.cloud.compoent.JSONResult;
import com.paste.cloud.compoent.SecurityUtil;
import com.paste.cloud.compoent.TokenUtil;
import com.paste.cloud.compoent.VerifyCodeCache;
import com.paste.cloud.dao.UserDAO;
import com.paste.cloud.dto.LogInDTO;
import com.paste.cloud.dto.RegisterDTO;
import com.paste.cloud.model.User;
import com.paste.cloud.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.UUID;

import static com.paste.cloud.compoent.Constants.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private VerifyCodeCache verifyCodeCache;
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public JSONObject register(RegisterDTO registerDTO) {
        String email = registerDTO.getEmail();
        User user = userDAO.findByEmail(email);
        if(user!=null){
            return JSONResult.getResult(userExistCode,userExistDesc);
        }
        user = new User();
        user.setEmail(email);
        user.setPwd(registerDTO.getPassword());
        user.setRegisterTime(new Date());
        user.setUid(UUID.randomUUID().toString().replace("-",""));
        userDAO.insert(user);
        return JSONResult.success_result;
    }

    @Override
    public JSONObject logIn(LogInDTO logInDTO) {
        User byEmail = userDAO.findByEmail(logInDTO.getEmail());
        if(byEmail==null){
            return JSONResult.getResult(userNotExistCode,userNotExistDesc);
        }

        if(logInDTO.getPwdIs() == 0){//验证码
            if(!verifyCodeCache.verifyCode(SecurityUtil.hash256(logInDTO.getEmail()+logInDTO.getPwd()))){
                return JSONResult.getResult(userLoginFailCode,userLoginFailDesc);
            }
        }else{
            if(!byEmail.getPwd().equalsIgnoreCase(logInDTO.getPwd())){
                return JSONResult.getResult(userLoginFailCode,userLoginFailDesc);
            }
        }
        //通过认证 开始分发token
        JSONObject result = new JSONObject();
        BeanUtils.copyProperties(JSONResult.success_result,result);
        String token = tokenUtil.getToken(byEmail.getEmail()+"分"+byEmail.getUid());
        try {
            result.put("token",token);
        } catch (JSONException e) {
            e.printStackTrace();
            return JSONResult.inner_error;
        }
        return result;
    }

}