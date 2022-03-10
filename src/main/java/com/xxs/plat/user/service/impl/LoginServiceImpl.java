package com.xxs.plat.user.service.impl;

import com.xxs.plat.user.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Map<String,Object> login(Map user) {
        Map<String, Object> resultMap = new HashMap<>();
        if ("xxs".equals(user.get("username")) && "123".equals(user.get("password"))) {
            resultMap.put("user", user);
            resultMap.put("status", 200);
        } else {
            resultMap.put("status", 500);
            resultMap.put("msg", "用户名或密码错误");
        }
        return resultMap;
    }
}
