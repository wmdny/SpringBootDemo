package com.xxs.common;

/**
 * 返回接口调用异常类
 */
public class ApiException extends RuntimeException{
    public ApiException(){
        super();
    }
    
    public ApiException(String message){
        super(message);
    }
    
    public ApiException(String message,Throwable exception){
        super(message,exception);
    }
    
    public ApiException(Throwable exception){
        super(exception);
    }
}
