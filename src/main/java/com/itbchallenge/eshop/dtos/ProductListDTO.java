package com.itbchallenge.eshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDTO {
    private ArrayList<ProductDTO> products;

    public void applyFilter(Predicate<ProductDTO> p1){
        this.products = new ArrayList<>(this.products.stream().filter(p1).collect(Collectors.toList()));
    }
}
