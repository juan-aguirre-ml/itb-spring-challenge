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
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImple implements ProductRepository {

    private AtomicInteger productIds = new AtomicInteger();
    private HashMap<Integer, ProductDTO> repo = new HashMap<>();

    @Override
    public void loadRepositoryFromDisk(String filename) {
        try {

            Reader csvFile = Files.newBufferedReader(Path.of(filename));
            CSVParser csvParser = new CSVParser(csvFile, CSVFormat.DEFAULT); //Apache commons csv module (added in the pom)

            for (CSVRecord record : csvParser) {
                ProductDTO prod = new ProductDTO();
                prod.setName(record.get("name"));
                prod.setCategory(record.get("category"));
                prod.setBrand(record.get("brand"));
                prod.setPrice(Integer.parseInt(record.get("price")));
                prod.setQuantity(Integer.parseInt(record.get("quantity")));
                prod.setFreeShipping(record.get("freeShipping").equals("SI"));
                prod.setPrestige(record.get("prestige").length());
                prod.setProductId(this.productIds.incrementAndGet());

                this.repo.put(this.productIds.get(), prod);
            }


        } catch (IOException e) {
            //TODO: Do something better here? -> http.internalerror?????
            e.printStackTrace();
        }


    }

    @Override
    public void saveRepositoryToDisk(HashMap<Integer, ProductDTO> repo, String filename) {
        //TODO: Implement serialization to CSV.
    }

    @Override
    public ArrayList<ProductDTO> getAllProducts() {
        //Returns a list of all the products in the repository.
        return new ArrayList<>(this.repo.values());
    }

    @Override
    public ProductDTO getProductById(int productId) {
        //Get a product by its productId from the repository.
        return this.repo.getOrDefault(productId, null);
    }

    //Filters products by 2 parameters.
    public ArrayList<ProductDTO> getProductBy(String param1, String value1, String param2, String value2) {
        //Filters using the specified parameters and values. Returns an arraylist of products
        //Example name = "Shoe", brand = "Nike"
        ArrayList<ProductDTO> arr = this.getAllProducts();
        ArrayList<ProductDTO> ret = null;

        //Get the correct filters from FilterBox class
        Predicate<ProductDTO> p1 = FilterBox.getFilter(param1, value1);
        Predicate<ProductDTO> p2 = FilterBox.getFilter(param2, value2);

        //Check if 2 filters are passed
        if (p1 != null && p2 != null) {
            ret = new ArrayList<ProductDTO>(arr.stream().filter(p1.and(p2)).collect(Collectors.toList()));
            return ret;
        } else
            return null;
    }

    //Filters products by only one parameter
    public ArrayList<ProductDTO> getProductsBy(String param1, String value1) {
        ArrayList<ProductDTO> arr = this.getAllProducts();
        ArrayList<ProductDTO> ret = null;
        //Get the correct filter
        Predicate<ProductDTO> p1 = FilterBox.getFilter(param1, value1);

        //Check if only one filter is passed
        if (p1 != null) {
            ret = new ArrayList<ProductDTO>(arr.stream().filter(p1).collect(Collectors.toList()));
            return ret;
        } else
            return null;
    }
}
