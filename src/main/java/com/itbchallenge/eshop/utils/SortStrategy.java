package com.itbchallenge.eshop.utils;

import com.itbchallenge.eshop.dtos.ProductDTO;

import java.util.ArrayList;

public interface SortStrategy {
    public ArrayList<ProductDTO> sort(ArrayList<ProductDTO> array);
}
