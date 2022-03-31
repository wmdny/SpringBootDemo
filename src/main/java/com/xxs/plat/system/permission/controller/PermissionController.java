package com.xxs.plat.system.permission.controller;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    UserService userService;
    
    
    @PostMapping("menuTree")
    @ResponseBody
    public Map<String, Object> login(HttpSession session) {
        try {
            Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
            //查出权限菜单
            List<Map<String, Object>> menuList = new ArrayList<>();
            Map<String, Object> resultMap = new HashMap<String, Object>(){{
                put("status", 200);
                put("data", menuList);
            }};
            return resultMap;
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
