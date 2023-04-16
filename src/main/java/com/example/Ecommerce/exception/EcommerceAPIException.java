package com.example.Ecommerce.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


public class EcommerceAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public EcommerceAPIException(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public EcommerceAPIException(String message, HttpStatus status, String message1){
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
