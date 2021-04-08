package com.itbchallenge.eshop.exceptions;

public class NotEnoughStockException extends Exception{
    public NotEnoughStockException(){
        super("There is not enough stock to fulfill the order.");
    }
}
