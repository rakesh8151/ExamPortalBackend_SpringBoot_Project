package com.example.exam.exceptions;

public class UserFoundException extends Exception{

    public UserFoundException(){
        super("User with this username is already present  , Try with other username");
    }
    public UserFoundException(String message){
        super(message);
    }
}
