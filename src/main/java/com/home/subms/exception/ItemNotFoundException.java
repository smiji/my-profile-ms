package com.home.subms.exception;

public class ItemNotFoundException extends CVBaseException{

    public ItemNotFoundException(String message) {
        super(message);
    }
    public ItemNotFoundException(String message, Exception exception) {
        super(message, exception);
    }
}
