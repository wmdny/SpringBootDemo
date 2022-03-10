package com.xxs.config;

import com.alibaba.fastjson.JSON;
import com.xxs.common.ApiException;
import com.xxs.utils.CommonUtil;
import com.xxs.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 拦截器：验证用户是否登录
 * @author hz
 */
public class Interceptor implements HandlerInterceptor {
    
    /***
     * 拦截器中不符合规则的直接返回错误结果
     * @param status
     * @param msg
     * @param response
     */
    private void retrunResult(int status,String msg,HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            // 设置响应类型
            response.setContentType("application/json;charset=utf-8");
            // 获取响应对象的Writer对象
            writer = response.getWriter();
            String s = JSON.toJSONString(CommonUtil.returnMap(status, msg));
            // 写出数据. writer()也可以
            writer.print(s);
        } catch (IOException e) {
            throw new ApiException(e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //http的header中获得token
        String token = request.getHeader(JWTUtils.USER_LOGIN_TOKEN);
        //token不存在
        if (token == null || "".equals(token)) {
            retrunResult(401, "请登录后再操作", response);
            return false;
        }
        //验证token
        String sub = null;
        try {
            //验证token
            sub = JWTUtils.validateToken(token);
        } catch (Exception e) {
            //验证的token已过期或发生异常
            retrunResult(401, e.getMessage(), response);
            return false;
        }
        if (sub == null || "".equals(sub)) {
            //token验证失败
            retrunResult(401, "身份信息验证失败，请重新登录", response);
            return false;
        }
        //更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtils.isNeedUpdate(token)) {
            String newToken = JWTUtils.createToken(sub);
            response.setHeader(JWTUtils.USER_LOGIN_TOKEN, newToken);
        }
        return true;
    }
}
