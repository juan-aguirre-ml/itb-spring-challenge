package com.itbchallenge.eshop.utils;

import com.itbchallenge.eshop.dtos.ProductDTO;
import com.itbchallenge.eshop.dtos.UserDTO;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterBox {
    //This is a lazy strategy pattern implementation
    public static Predicate<ProductDTO> getProductFilter(String type,String query){
        switch (type) {
            case "product":
                return e -> e.getName().equals(query);
            case "brand":
                return e -> e.getBrand().equals(query);
            case "category":
                return e -> e.getCategory().equals(query);
            case "price":
                //Todo translate price correctly to integer
                return e -> e.getPrice() == Integer.parseInt(query);
            case "freeShipping":
                return e -> e.isFreeShipping() == query.equals("SI");
            case "prestige":
                return e -> e.getPrestige() == query.length();
            default:
                return null;
        }
    }

    public static ArrayList<ProductDTO> applyProductFilter(ArrayList<ProductDTO> products, Predicate<ProductDTO> p1){
        if (p1 != null)
            return new ArrayList<ProductDTO>(products.stream().filter(p1).collect(Collectors.toList()));
        else
            return products;
    }

    public static Predicate<UserDTO> getUserFilter(String type,String query){
        switch (type) {
            case "state":
                return e -> e.getState().equals(query);
            case "firstName":
                return e -> e.getFirstName().equals(query);
            default:
                return null;
        }
    }

    public static ArrayList<UserDTO> applyUserFilter(ArrayList<UserDTO> users, Predicate<UserDTO> p1){
        if (p1 != null)
            return new ArrayList<UserDTO>(users.stream().filter(p1).collect(Collectors.toList()));
        else
            return users;
    }
}
