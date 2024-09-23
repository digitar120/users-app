package com.digitar120.usersapp.exception.globalhandler;

import org.springframework.http.HttpStatus;

public class CustomHttpStatusException extends RuntimeException{
    public CustomHttpStatusException(String message){ super(message);}

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }
}
