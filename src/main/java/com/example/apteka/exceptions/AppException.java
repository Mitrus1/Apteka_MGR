package com.example.apteka.exceptions;

// klasa odpowiada za wyjątki w aplikacji
public class AppException extends RuntimeException{
    public AppException(String message)
    {
        super(message);
    }
}
