package com.itbchallenge.eshop.utils;

import com.itbchallenge.eshop.dtos.ProductDTO;

import java.util.ArrayList;
import java.util.Comparator;

public class SortAlphaAsc implements SortStrategy{

    public Comparator<ProductDTO> ProductAlphaAsc = new Comparator<ProductDTO>() {
        @Override
        public int compare(ProductDTO o1, ProductDTO o2) {
            String productName1 = o1.getName();
            String productName2 = o2.getName();

            return productName1.compareTo(productName2);
        }
    };
    @Override
    public ArrayList<ProductDTO> sort(ArrayList<ProductDTO> array) {
        array.sort(ProductAlphaAsc);
        return array;
    }


}
