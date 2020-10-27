package com.adrian_hartley.url_shortner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceFoundException extends RuntimeException{
    public ResourceFoundException(String message)
    {
        super("Error: " + message);
    }
}
