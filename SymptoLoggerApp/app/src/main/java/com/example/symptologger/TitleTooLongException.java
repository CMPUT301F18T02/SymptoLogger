package com.example.symptologger;

public class TitleTooLongException extends Exception{
    TitleTooLongException(){
        super("Title too long: 30 characters maximum!");
    }
}
