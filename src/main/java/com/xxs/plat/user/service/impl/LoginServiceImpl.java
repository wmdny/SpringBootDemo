package com.xxs.plat.user.service.impl;

import com.xxs.plat.user.service.LoginService;
import com.xxs.utils.JWTUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Map<String, Object> login(Map user) {
        Map<String, Object> resultMap = new HashMap<>();
        if ("xxs".equals(user.get("userName")) && "123".equals(user.get("password"))) {
            user.put("userId", "0001");
            String token = JWTUtils.createToken(String.valueOf(user.get("userId")));
            user.put("token", token);
            resultMap.put("user", user);
            resultMap.put("status", 200);
        } else {
            resultMap.put("status", 500);
            resultMap.put("msg", "用户名或密码错误");
        }
        return resultMap;
    }
}
