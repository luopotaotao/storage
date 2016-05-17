package com.l1.admin.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by luopotaotao on 2016/5/9.
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
    private String captcha;

    // 省略 getter 和 setter 方法

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CaptchaUsernamePasswordToken(String username, String password,
                                        boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }
}
