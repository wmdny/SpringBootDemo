package com.xxs.utils;

import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommonUtil {
    
    
    //获取配置文件
    public static String getProperty(String key) {
        String value = "";
        try {
            ClassPathResource classPathResource = new ClassPathResource("cfg/config.properties");
            Properties properties = new Properties();
            properties.load(classPathResource.getInputStream());
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
    
    
    /**
     * MD5 加密
     *
     * @param str
     * @return
     *
     * @throws Exception
     */
    public static String getMD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    
        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        for (byte b : byteArray) {
            if (Integer.toHexString(0xFF & b).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & b));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & b));
            }
        }
        return md5StrBuff.toString();
    }
    
    /**
     * 获取远程Ip
     */
    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
    
    /**
     * 获取操作系统,浏览器及浏览器版本信息
     *
     * @param request
     * @return
     */
    public static String getOsAndBrowserInfo(HttpServletRequest request) {
        String browserDetails = request.getHeader("User-Agent");
        String userAgent = browserDetails;
        String user = userAgent.toLowerCase();
        
        String os = "";
        String browser = "";
        
        //=================OS Info=======================
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else {
            os = "UnKnown, More-Info: " + userAgent;
        }
        //===============Browser===========================
        if (user.contains("edge")) {
            browser = (userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("msie")) {
            String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]
                    + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera")) {
                browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]
                        + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                        .replace("OPR", "Opera");
            }
            
        } else if (user.contains("chrome")) {
            browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) ||
                (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) ||
                (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            browser = "Netscape-?";
            
        } else if (user.contains("firefox")) {
            browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
            browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
        } else {
            browser = "UnKnown, More-Info: " + userAgent;
        }
        
        return os + "-" + browser;
    }
    
    /**
     * 提供精确的乘法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数的数学积，以字符串格式返回
     */
    
    public static String multiply(String v1, String v2) {
        
        BigDecimal b1 = new BigDecimal(v1);
        
        BigDecimal b2 = new BigDecimal(v2);
        
        return b1.multiply(b2).toString();
        
    }
    
    /**
     * 提供精确的乘法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的积
     */
    
    public static double multiply(double v1, double v2) {
        
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        
        return b1.multiply(b2).doubleValue();
        
    }
    
    /**
     * 提供精确的减法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数数学差，以字符串格式返回
     */
    
    public static String subtract(String v1, String v2) {
        
        BigDecimal b1 = new BigDecimal(v1);
        
        BigDecimal b2 = new BigDecimal(v2);
        
        return b1.subtract(b2).toString();
        
    }
    
    /**
     * 提供精确的减法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的差
     */
    
    public static double subtract(double v1, double v2) {
        
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        
        return b1.subtract(b2).doubleValue();
        
    }
    
    /**
     * 提供精确的加法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数数学加和，以字符串格式返回
     */
    
    public static String add(String v1, String v2) {
        
        BigDecimal b1 = new BigDecimal(v1);
        
        BigDecimal b2 = new BigDecimal(v2);
        
        return b1.add(b2).toString();
        
    }
    
    /**
     * 提供精确的加法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的和
     */
    
    public static double add(double v1, double v2) {
        
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        
        return b1.add(b2).doubleValue();
        
    }
    
    /**
     * 作为service或controller，Map的返回
     */
    public static Map<String, Object> returnMap(Map<String, Object> map, int status, Object info) {
        map.put("status", status);
        map.put("msg", info.toString());
        return map;
    }
    
    public static Map<String, Object> returnMap(int status, Object info) {
        Map<String, Object> map = new HashMap<>();
        return returnMap(map, status, info);
    }
    
    public static boolean isNotEmptyString(String str) {
        return !("".equals(str) || "null".equals(str));
    }
}
