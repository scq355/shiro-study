package com.wowjoy.boot.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author scq
 * @date 2020-08-07 10:42:00
 */
@Controller
public class IndexController {
    @RequestMapping("index")
    public String index() {
        return "home";
    }
}
