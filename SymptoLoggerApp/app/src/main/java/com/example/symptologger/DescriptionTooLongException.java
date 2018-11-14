package com.example.symptologger;

public class DescriptionTooLongException extends Exception {
    DescriptionTooLongException(){
        super("Description too long: 300 characters maximum");
    }
}
