package com.itbchallenge.eshop.controllers;

import com.itbchallenge.eshop.dtos.*;
import com.itbchallenge.eshop.exceptions.InvalidItemException;
import com.itbchallenge.eshop.services.EshopService;
import com.itbchallenge.eshop.services.PurchaseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EshopController {

    @Autowired
    private EshopService eshopService;

    @Autowired
    private PurchaseRequestService ticketService;

    @GetMapping("/articles")
    public ArrayList<ProductDTO> getProducts(@RequestParam Map<String,String> queryParams){
        ArrayList<ProductDTO> arr = eshopService.getProducts((HashMap<String, String>) queryParams);
        return arr;
    }

    @PostMapping("/purchase-request")
    public PurchaseRequestDTO postPurchaseRequest(@RequestBody ArticlesDTO articles) throws InvalidItemException {
        PurchaseRequestDTO purchaseRequest = ticketService.addPurchaseRequest(articles);
        return purchaseRequest;
    }

    @ExceptionHandler(InvalidItemException.class)
    public ResponseEntity handlerException(InvalidItemException exception){
        StatusCodeDTO error = new StatusCodeDTO();
        error.setMessage(exception.getMessage());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(Exception exception){
        ErrorDTO error = new ErrorDTO();
        error.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
