package com.example.Litmus_project.customException;

public class DuplicateEmailException extends RuntimeException  {

    public DuplicateEmailException(String message) {
        super(message);
    }
}



