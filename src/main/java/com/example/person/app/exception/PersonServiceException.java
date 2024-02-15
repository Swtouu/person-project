package com.example.person.app.exception;

import com.example.person.core.exception.ServiceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class PersonServiceException extends ServiceException {
    public PersonServiceException(HttpStatus statusCode, PersonErrorCode errorCode, Throwable cause, String message, Object... args){
        super(statusCode, errorCode.name(), cause, message, args);
    }

    public PersonServiceException(HttpStatus statusCode, PersonErrorCode errorCode, String message, Object... args){
        super(statusCode, errorCode.name(), null, message, args);
    }
}
