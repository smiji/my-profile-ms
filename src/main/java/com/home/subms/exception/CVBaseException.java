package com.home.subms.exception;


public class CVBaseException extends RuntimeException{

    public CVBaseException(String message){
        super(message);
    }

    public CVBaseException(String message,Exception exception){
        super(message,exception);
    }
}
