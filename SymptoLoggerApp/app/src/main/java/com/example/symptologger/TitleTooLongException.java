package com.example.symptologger;

/**
 * Exception thrown when title of a Concern is longer then 30 characters
 */
public class TitleTooLongException extends Exception{
    TitleTooLongException(){
        super("Title too long: 30 characters maximum!");
    }
}
