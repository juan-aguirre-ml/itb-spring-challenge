package com.itbchallenge.eshop.repositories;

import com.itbchallenge.eshop.dtos.ProductDTO;
import com.itbchallenge.eshop.exceptions.InternalErrorException;

import java.util.ArrayList;
import java.util.HashMap;

public interface ProductRepository {

    public void loadRepositoryFromDisk(String filename) throws InternalErrorException;

    public void saveRepositoryToDisk(HashMap<Integer,ProductDTO> repo, String filename);

    public ArrayList<ProductDTO> getAllProducts();

    public ProductDTO getProductById(int productId);

    //public ArrayList<ProductDTO> getProductBy(String param1, String value1, String param2, String value2);

    public ArrayList<ProductDTO> getProductsBy(String param1, String value1);


}
