package com.xxs.plat.user.controller;

import com.xxs.plat.user.service.LoginService;
import com.xxs.utils.CommonUtil;
import com.xxs.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    LoginService loginService;
    
    
    @PostMapping("login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map user, HttpServletResponse response) {
        try {
            if (!CommonUtil.isNotEmptyString(user.get("userName") + "")) {
                return CommonUtil.returnMap(500, "手机号不合法");
            }
            Map<String, Object> resultMap = loginService.login(user);
            //将token存入Http的header中
            if ("200".equals(String.valueOf(resultMap.get("status")))) {
                response.setHeader(JWTUtils.USER_LOGIN_TOKEN, String.valueOf(((Map) resultMap.get("user")).get("token")));
                return CommonUtil.returnMap(resultMap, 200, "登录成功");
            } else {
                return resultMap;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return CommonUtil.returnMap(500, e);
        }
    }
    
    @PostMapping("logOut")
    @ResponseBody
    public Map<String, Object> logOut(@RequestBody Map user, HttpServletResponse response) {
        try {
            if (!CommonUtil.isNotEmptyString(user.get("userName") + "")) {
                return CommonUtil.returnMap(500, "手机号不合法");
            }
            Map<String, Object> resultMap = loginService.login(user);
            //将token存入Http的header中
            if ("200".equals(String.valueOf(resultMap.get("status")))) {
                String token = JWTUtils.createToken(String.valueOf(user.get("userName")));
                response.setHeader(JWTUtils.USER_LOGIN_TOKEN, token);
                user.put("token", token);
                return CommonUtil.returnMap(resultMap, 200, "登录成功");
            } else {
                return resultMap;
            }
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
