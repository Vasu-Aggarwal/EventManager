package com.bookevent.BookEventManager.exceptions;

public class BadRequest extends RuntimeException{
    public BadRequest(){
        super("Resource already exists");
    }

    public BadRequest(String resource){
        super(resource+" already exists");
    }

    public BadRequest(String resource, String message){
        super(resource+message);
    }
}
