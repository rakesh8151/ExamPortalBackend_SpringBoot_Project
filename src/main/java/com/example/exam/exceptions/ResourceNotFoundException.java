package com.example.exam.exceptions;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(){
        super("User with this username is not found");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }

}
