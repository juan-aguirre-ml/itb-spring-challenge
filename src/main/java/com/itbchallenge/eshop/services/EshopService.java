package com.itbchallenge.eshop.services;

import com.itbchallenge.eshop.dtos.ProductDTO;
import com.itbchallenge.eshop.repositories.ProductRepository;
import com.itbchallenge.eshop.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class EshopService {

    @Autowired
    private ProductRepository productRepository;

    public ArrayList<ProductDTO> getProducts(HashMap<String,String> arguments){

        SortStrategy sortStrat = null;
        ArrayList<ProductDTO> productos = null;

        if (!arguments.isEmpty()) {
            for (String key : arguments.keySet()) {
                if (!key.equals("order"))
                    productos = productRepository.getProductBy(key,arguments.get(key));
            }
        }else
            return productRepository.getAllProducts();

        //Using a Strategy pattern to select the sorting method
        String stratNumb = arguments.get("order");
        sortStrat = SortStrategyPicker.pickStrategy(stratNumb);

        return null;
    }


}
