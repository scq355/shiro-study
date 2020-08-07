package com.wowjoy.boot.shiro.controller;

import com.wowjoy.boot.shiro.entity.User;
import com.wowjoy.boot.shiro.service.IUserService;
import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author scq
 * @date 2020-08-07 10:47:00
 */
@Log
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        String code = "";
        session.setAttribute("code", code);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/png");
    }

    @RequestMapping("registerview")
    public String registerView() {
        return "register";
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/user/loginview";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/user/registerview";
        }
    }

    @RequestMapping("loginview")
    public String loginView() {
        return "login";
    }

    /**
     * 处理身份认证
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            log.info(e.getMessage());
            model.addAttribute("msg", "用户名不存在");
        } catch (IncorrectCredentialsException e) {
            log.info(e.getMessage());
            model.addAttribute("msg", "密码错误");
        }
        return "redirect:/user/loginview";
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/user/loginview";
    }
}
