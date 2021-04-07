package com.itbchallenge.eshop.services;

import com.itbchallenge.eshop.dtos.ProductDTO;
import com.itbchallenge.eshop.repositories.ProductRepository;
import com.itbchallenge.eshop.utils.FilterBox;
import com.itbchallenge.eshop.utils.SortStrategy;
import com.itbchallenge.eshop.utils.SortStrategyPicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

@Service
public class EshopService {
    @Autowired
    private ProductRepository productRepository;


    public ArrayList<ProductDTO> getProducts(HashMap<String,String> arguments){
        SortStrategy sortStrat = null;
        ArrayList<ProductDTO> productos = productRepository.getAllProducts();


        if (!arguments.isEmpty()) {
            for (String key : arguments.keySet()) {

                if (!key.equals("order")){

                    //Applying the necesary filters
                    Predicate<ProductDTO> p1 = FilterBox.getFilter(key,arguments.get(key));
                    productos = FilterBox.applyFilter(productos,p1);

                }if (key.equals("order")){

                    //Using a Strategy pattern to select the sorting method and sorting.
                    String stratNumb = arguments.get("order");
                    sortStrat = SortStrategyPicker.pickStrategy(stratNumb);
                    productos = sortStrat.sort(productos);
                }

            }
            return productos;
        }else
            return productos;
    }


}
