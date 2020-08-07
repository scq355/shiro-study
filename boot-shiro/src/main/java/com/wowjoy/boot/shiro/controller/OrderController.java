package com.wowjoy.boot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author scq
 * @date 2020-08-07 11:58:00
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @RequestMapping("save")
    @RequiresRoles(value = {"user, admin"})
//    @RequiresPermissions("user:update:01")
    public String save() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            System.out.println("保存订单");
        } else {
            System.out.println("无权访问");
        }
        return "home";
    }
}
