package com.xxs.plat.system.menu.controller;

import com.xxs.plat.system.menu.service.MenuService;
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
@RequestMapping("menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    
    
    @PostMapping("menuTree")
    @ResponseBody
    public Map<String, Object> login(HttpSession session) {
        try {
            Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
            return menuService.menuTree(userMap);
        } catch (RuntimeException e) {
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
