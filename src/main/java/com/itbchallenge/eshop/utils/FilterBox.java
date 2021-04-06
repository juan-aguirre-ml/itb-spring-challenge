package com.itbchallenge.eshop.utils;

import com.itbchallenge.eshop.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
@NoArgsConstructor
public class FilterBox {
    public static Predicate<ProductDTO> getFilter(String type,String query){
        switch (type) {
            case "product":
                return e -> e.getName().equals(query);
            case "brand":
                return e -> e.getBrand().equals(query);
            case "category":
                return e -> e.getCategory().equals(query);
            case "price":
                return e -> e.getPrice() == Integer.parseInt(query);
            case "freeShipping":
                return e -> e.isFreeShipping() == query.equals("SI");
            case "prestige":
                return e -> e.getPrestige() == query.length();
            default:
                return null;
        }
    }
}
