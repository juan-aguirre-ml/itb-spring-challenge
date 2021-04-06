package com.itbchallenge.eshop.utils;

public class SortStrategyPicker {
    public static SortStrategy pickStrategy(String stratNumb){
        switch (stratNumb){
            case "0":
                return new SortAlphaAsc();
            case "1":
                return new SortAlphaDesc();
            case "2":
                return new SortPriceAsc();
            case "3":
                return new SortPriceDesc();
            default:
                return null;
        }
    }
}
