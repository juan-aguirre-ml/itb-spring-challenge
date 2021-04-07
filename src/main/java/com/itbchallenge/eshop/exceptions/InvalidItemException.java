package com.itbchallenge.eshop.exceptions;

public class InvalidItemException extends Exception{
    public InvalidItemException(){
        super("The item was not found.");
    }
}
