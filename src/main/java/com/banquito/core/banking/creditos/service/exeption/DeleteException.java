package com.banquito.core.banking.creditos.service.exeption;

public class DeleteException extends RuntimeException {
    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Exception cause) {
        super(message, cause);    
    }
}
