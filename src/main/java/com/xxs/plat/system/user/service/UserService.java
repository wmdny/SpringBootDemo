package com.xxs.plat.system.user.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    Map<String,Object> login(Map<String,Object> user, HttpServletResponse response, HttpSession session);
    
    Map<String, Object> list(Map<String,Object> param);
    
    Map<String, Object> info(Map<String, Object> param);
}
