package com.xxs.plat.system.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxs.plat.system.user.dao.PlatUserMapper;
import com.xxs.plat.system.user.model.PlatUser;
import com.xxs.plat.system.user.service.UserService;
import com.xxs.utils.CommonUtil;
import com.xxs.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    
    @Resource
    PlatUserMapper userMapper;
    
    @Override
    public Map<String, Object> login(Map<String,Object> param, HttpServletResponse response, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (!(param.containsKey("loginName") && param.containsKey("password"))) {
                return CommonUtil.returnMap(500, "登录名或密码不全");
            }
            PlatUser platUser = userMapper.selectByLoginName(param.get("loginName").toString());
            if (null != platUser) {
                if (platUser.getPassword().equals(param.get("password"))) {
                    String token = JWTUtils.createToken(platUser.getLoginName() + platUser.getId());
                    Map<String, Object> userMap = JSON.parseObject(JSON.toJSONString(platUser));
                    userMap.put("token", token);
                    userMap.put("userId", platUser.getId());
                    resultMap.put("user", userMap);
                    response.setHeader(JWTUtils.USER_LOGIN_TOKEN, token);
                    session.setAttribute("user",userMap);
                    return CommonUtil.returnMap(resultMap, 200, "登录成功");
                } else {
                    return CommonUtil.returnMap(500, "用户名或密码错误");
                }
            } else {
                return CommonUtil.returnMap(500, "不存在该用户");
            }
        } catch (Exception e) {
            log.error("用户登录失败：{}", param, e);
            throw new RuntimeException(e);
        }
        
    }
    
    @Override
    public Map<String, Object> list(Map<String,Object> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            PageHelper.startPage(param.containsKey("pageNo") ? Integer.parseInt(param.get("pageNo").toString()) : 1,
                    param.containsKey("pageSize") ? Integer.parseInt(param.get("pageSize").toString()) : 999);
            List<Map<String, Object>> list = userMapper.list(param);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
            resultMap.put("data", pageInfo);
            return CommonUtil.returnMap(resultMap, 200, "查询成功");
        } catch (NumberFormatException e) {
            log.error("查询用户列表错误:{}", param);
            throw new RuntimeException(e);
        }
    }
    @Override
    public Map<String, Object> info(Map<String,Object> param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (!param.containsKey("id")) {
                return CommonUtil.returnMap(500, "缺少参数");
            }
            PlatUser platUser = userMapper.selectByPrimaryKey(Integer.parseInt(param.get("id").toString()));
            resultMap.put("data", platUser);
            return CommonUtil.returnMap(resultMap, 200, "查询成功");
        } catch (NumberFormatException e) {
            log.error("查询用户详情错误:{}", param);
            throw new RuntimeException(e);
        }
    }
}
