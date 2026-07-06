package com.home.subms.exception;

public class ConfigMissingException extends CVBaseException {
    public ConfigMissingException(String message) {
        super(message);
    }

    public ConfigMissingException(String message, Exception exception) {
        super(message, exception);
    }
}
