package com.example.symptologger;

public class UserIDTooShortException extends Exception {
    UserIDTooShortException(){
        super("User ID too short! Must be at least 8 characters.");
    }
}
