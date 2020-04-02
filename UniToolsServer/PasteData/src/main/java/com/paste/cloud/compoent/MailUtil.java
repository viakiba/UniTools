package com.paste.cloud.compoent;

import com.google.common.base.Preconditions;
import io.github.biezhi.ome.OhMyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

import static io.github.biezhi.ome.OhMyEmail.SMTP_QQ;

/**
* 邮件发送工具
* @author viakiba
* @date 2019/12/18
*/
@Component
public class MailUtil implements CommandLineRunner {

    @Value("${email.account}")
    private String emailAccount;
    @Value("${email.password}")
    private String emailPassword;
    @Value("${email.verify.code.size}")
    private int verifyCodeSize;
    @Autowired
    private VerifyCodeCache verifyCodeCache;

    public static final String code[] = {"0","1","2","3","4","5","6","7","8","9"};

    /**
     * 发送验证码用的
     * [email]
     * @return void
     * @author viakiba
     * @date 2019/12/18
     */
    public void sendVerifyCode(String email) throws Exception {
        OhMyEmail.subject("粘贴云验证码")
                .from("粘贴云")
                .to(email)
                .text("信件内容")
                .send();
        String randomCode = getRandomCode(verifyCodeSize);
        String s = SecurityUtil.hash256(email + randomCode);
        verifyCodeCache.putVerifyCode(s,"0");
    }

    /**
     * 随机验证码的
     * [size]
     * @return java.lang.String
     * @author viakiba
     * @date 2019/12/18
     */
    public String getRandomCode(int size){
        Preconditions.checkArgument(size<4,"内部异常");
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<size;i++){
            sb.append(code[random.nextInt(code.length)]);
        }
        return sb.toString();
    }

    /**
     * 启动初始化用的
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        OhMyEmail.config(SMTP_QQ(true), emailAccount, emailPassword);
    }
}
