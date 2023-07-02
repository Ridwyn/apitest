package com.santander.santanderapp.exception;

public class DatabaseException extends RuntimeException{

    public DatabaseException (Throwable cause){
        super(cause);
    }

    public  DatabaseException (String msg){
        super(msg);
    }
}
