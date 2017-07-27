package com.tryking.web;

import com.tryking.domain.User;
import com.tryking.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    private UserService userService;
    private Log log = LogFactory.getLog(LoginController.class);

    /**
     * 用户访问首页的时候，自动跳转到“login”页面
     *
     * @return login页面
     */
    @RequestMapping(value = "/")
    public String loginPage() {
        log.error("********进入index.html页面***********");
        return "login";
    }

    /**
     * @param request request对象
     * @param command 用户输入的用户名、密码对象
     * @return 登录成功页面/失败信息
     */
    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand command) {
        log.error("********进入loginCheck.html页面***********");
        boolean isValidUser = userService.hasMatchUser(command.getUserName(), command.getPassword());

        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名密码不符");
        } else {
            User user = userService.findUserByUserName(command.getUserName());
            //登录成功，加积分。
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
