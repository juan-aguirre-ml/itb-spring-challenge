package com.itbchallenge.eshop.repositories;

import com.itbchallenge.eshop.dtos.ProductDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ProductRepository {

    public void loadRepositoryFromDisk(String filename);

    public void saveRepositoryToDisk(HashMap<Integer,ProductDTO> repo, String filename);

    public ArrayList<ProductDTO> getAllProducts();

    public ProductDTO getProductById(int productId);

    public ArrayList<ProductDTO> getProductBy(String param1, String value1, String param2, String value2);

    public ArrayList<ProductDTO> getProductBy(String param1, String value1);


}
