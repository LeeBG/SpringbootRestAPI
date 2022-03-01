package com.rest.api.advice.exception;

/**
 * Custom Exception 정의
 * CUserNotFound에서 C는 Custom을 의미한다.
 */
public class CUserNotFoundException extends RuntimeException{
    //CUserNotFoundException은 RuntimeExeption을 상속받는다.
    public CUserNotFoundException(String msg,Throwable t){
        super(msg,t);
    }
    public CUserNotFoundException(String msg){
        super(msg);
    }

    public CUserNotFoundException(){
        super();
    }
}
