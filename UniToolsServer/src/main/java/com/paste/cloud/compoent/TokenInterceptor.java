package com.paste.cloud.compoent;

import com.nimbusds.jose.util.Base64URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)||tokenUtil.verifyToken(token)){
            return false;
        }
        String[] split = new Base64URL(token.split("\\.")[1]).decodeToString().split("分");
        request.setAttribute("email", split[0]);
        request.setAttribute("uid", split[1]);

        request.setAttribute("time",System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("请求耗时 ："+(System.currentTimeMillis() - Long.parseLong(request.getAttribute("time").toString()))/1000);
        super.afterCompletion(request, response, handler, ex);
    }
}
