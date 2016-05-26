package com.l1.admin.contoller;

import com.l1.admin.domain.LoginResult;
import com.l1.admin.exception.IncorrectCaptchaException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;

/**
 * Created by luopotaotao on 2016/4/15.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResult login(@RequestParam String username, @RequestParam String password) {
        return getLoginResult();
    }

    @RequestMapping(value = "/unauthorised")
    public String unauthorised(Model model) {
        model.addAttribute("msg",getLoginResult().getMsg());
        return "unauthorised";
    }

    private LoginResult getLoginResult(){
        LoginResult result = new LoginResult();
        Subject user = SecurityUtils.getSubject();
        String expMsg = null;
        ServletRequest request = ((WebDelegatingSubject) user).getServletRequest();

        Object obj = request.getAttribute(
                org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        AuthenticationException authExp = (AuthenticationException) obj;
        if (authExp != null) {
            if (authExp instanceof UnknownAccountException ||
                    authExp instanceof IncorrectCredentialsException) {
                expMsg = "错误的用户账号或密码！";
            } else if (authExp instanceof IncorrectCaptchaException) {
                expMsg = "验证码错误！";
            } else {
                expMsg = "登录异常 :" + authExp.getMessage();
            }
        }

        result.setFlag(expMsg==null);
        result.setMsg(expMsg);
        return result;
    }
}
