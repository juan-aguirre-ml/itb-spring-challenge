package com.itbchallenge.eshop.repositories;

import com.itbchallenge.eshop.dtos.ProductDTO;
import com.itbchallenge.eshop.utils.FilterBox;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.logging.Filter;

@Repository
public class ProductRepositoryImple implements ProductRepository{

    private AtomicInteger productIds = new AtomicInteger();
    private HashMap<Integer,ProductDTO> repo = new HashMap<>();

    @Override
    public void loadRepositoryFromDisk(String filename) {
        try {

            Reader csvFile = Files.newBufferedReader(Path.of(filename));
            CSVParser csvParser = new CSVParser(csvFile, CSVFormat.DEFAULT);

            for (CSVRecord record:csvParser){
                ProductDTO prod = new ProductDTO();
                prod.setName(record.get("name"));
                prod.setCategory(record.get("category"));
                prod.setBrand(record.get("brand"));
                prod.setPrice(Integer.parseInt(record.get("price")));
                prod.setQuantity(Integer.parseInt(record.get("quantity")));
                prod.setFreeShipping(record.get("freeShipping").equals("SI"));
                prod.setPrestige(record.get("prestige").length());
                prod.setProductId(this.productIds.incrementAndGet());

                this.repo.put(this.productIds.get(),prod);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void saveRepositoryToDisk(HashMap<Integer, ProductDTO> repo, String filename) {

    }

    @Override
    public ArrayList<ProductDTO> getAllProducts() {
        return new ArrayList<>(this.repo.values());
    }

    @Override
    public ProductDTO getProductById(int productId) {
        return this.repo.get(productId);
    }

    public ArrayList<ProductDTO> getProductBy(String param1, String value1, String param2, String value2){
        ArrayList<ProductDTO> arr = this.getAllProducts();
        ArrayList<ProductDTO> ret = null;
        Predicate<ProductDTO> p1 = FilterBox.getFilter(param1,value1);
        Predicate<ProductDTO> p2 = FilterBox.getFilter(param2,value2);

        if (FilterBox.getFilter(param1, value1) != null)
            ret = (ArrayList<ProductDTO>) arr.stream().filter(p1.and(p2));

    }

}
