package com.itbchallenge.eshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EshopController {

    @GetMapping("/api/v1/articles")
    public void getproducts(@RequestParam Map<String,String> queryParams){

    }
}
