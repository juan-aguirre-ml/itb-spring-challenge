package com.itbchallenge.eshop.repositories;

import com.itbchallenge.eshop.dtos.ProductDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ProductRepository {

    public void loadRepositoryFromDisk(String filename);

    public void saveRepositoryToDisk(HashMap<Integer,ProductDTO> repo, String filename);

    public ArrayList<ProductDTO> getAllProducts();

    public ProductDTO getProductById(int productId);


}
