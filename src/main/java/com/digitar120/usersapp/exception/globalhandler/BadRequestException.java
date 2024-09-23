package com.digitar120.usersapp.exception.globalhandler;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomHttpStatusException{
    public BadRequestException(String message){
        super(message);
    }

    public HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
