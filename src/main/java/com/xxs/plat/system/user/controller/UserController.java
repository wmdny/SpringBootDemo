package com.xxs.plat.system.user.controller;

import com.xxs.plat.system.user.service.UserService;
import com.xxs.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    
    
    @PostMapping("login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String,Object> user, HttpServletResponse response, HttpSession session) {
        try {
            return userService.login(user, response, session);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return CommonUtil.returnMap(500, e);
        }
    }
    @PostMapping("list")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String,Object> param) {
        try {
            return userService.list(param);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return CommonUtil.returnMap(500, e);
        }
    }
    @PostMapping("info")
    @ResponseBody
    public Map<String, Object> info(@RequestBody Map<String,Object> param) {
        try {
            return userService.info(param);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return CommonUtil.returnMap(500, e);
        }
    }
    
    @PostMapping("logOut")
    @ResponseBody
    public Map<String, Object> logOut(HttpSession session) {
        try {
            session.removeAttribute("user");
            return CommonUtil.returnMap(200, "登出成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return CommonUtil.returnMap(500, e);
        }
    }
    
    @PostMapping("test")
    @ResponseBody
    public Map<String, Object> test(@RequestBody Map user, HttpServletResponse response) {
        try {
            Map map = new HashMap();
            map.put("name", "测试成功");
            return CommonUtil.returnMap(200, map);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return CommonUtil.returnMap(500, e);
        }
    }
}
