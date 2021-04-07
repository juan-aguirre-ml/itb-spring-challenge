package com.itbchallenge.eshop.controllers;

import com.itbchallenge.eshop.dtos.ProductDTO;
import com.itbchallenge.eshop.services.EshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EshopController {

    @Autowired
    private EshopService eshopService;

    @GetMapping("/articles")
    public ArrayList<ProductDTO> getProducts(@RequestParam Map<String,String> queryParams){
        ArrayList<ProductDTO> arr = eshopService.getProducts((HashMap<String, String>) queryParams);
        return arr;
    }
}
