package com.itbchallenge.eshop.utils;

import com.itbchallenge.eshop.dtos.ProductDTO;

import java.util.ArrayList;
import java.util.Comparator;

public class SortPriceAsc implements SortStrategy{

    public Comparator<ProductDTO> ProductPriceAsc = new Comparator<ProductDTO>() {
        @Override
        public int compare(ProductDTO o1, ProductDTO o2) {
            Float productPrice1 = o1.getPrice();
            Float productPrice2 = o2.getPrice();

            return productPrice1.compareTo(productPrice2);
        }
    };

    @Override
    public ArrayList<ProductDTO> sort(ArrayList<ProductDTO> array) {
        array.sort(ProductPriceAsc);
        return array;
    }
}
