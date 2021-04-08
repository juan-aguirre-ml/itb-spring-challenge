package com.itbchallenge.eshop.repositories;

import com.itbchallenge.eshop.dtos.ProductDTO;
import com.itbchallenge.eshop.exceptions.InternalErrorException;
import com.itbchallenge.eshop.utils.FilterBox;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImple implements ProductRepository {

    private AtomicInteger productIds = new AtomicInteger();
    private HashMap<Integer, ProductDTO> repo = new HashMap<>();

    public ProductRepositoryImple() throws InternalErrorException {
        this.loadRepositoryFromDisk("dbProductos.csv");
    }

    @Override
    public void loadRepositoryFromDisk(String filename) throws InternalErrorException {
        try {
            //File file = ResourceUtils.getFile("classpath:dbProductos.csv");
            //System.out.println(file.toString());

            Resource res = new ClassPathResource(filename);
            InputStream in = res.getInputStream();
            Reader csvFile = new InputStreamReader(in);
            CSVParser csvParser = new CSVParser(csvFile, CSVFormat.DEFAULT.withHeader()); //Apache commons csv module (added in the pom)

            for (CSVRecord record : csvParser) {
                ProductDTO prod = new ProductDTO();
                prod.setName(record.get("name"));
                prod.setCategory(record.get("category"));
                prod.setBrand(record.get("brand"));
                prod.setPrice(Float.parseFloat(record.get("price").replaceAll("[$.]","")));
                prod.setQuantity(Integer.parseInt(record.get("quantity")));
                prod.setFreeShipping(record.get("freeShipping").equals("SI"));
                prod.setPrestige(record.get("prestige").length());
                prod.setProductId(this.productIds.incrementAndGet());

                this.repo.put(this.productIds.get(), prod);
            }


        } catch (IOException e) {
            //TODO: Do something better here? -> http.internalerror?????
            throw new InternalErrorException();
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

    //Filters products by one parameter.
    //Todo: This may not be used after all. Check and delete if necesary.
    @Override
    public ArrayList<ProductDTO> getProductsBy(String param1, String value1) {
        ArrayList<ProductDTO> arr = this.getAllProducts();
        ArrayList<ProductDTO> ret = null;
        //Get the correct filter
        Predicate<ProductDTO> p1 = FilterBox.getProductFilter(param1, value1);

        //Check if only one filter is passed
        if (p1 != null) {
            ret = new ArrayList<ProductDTO>(arr.stream().filter(p1).collect(Collectors.toList()));
            return ret;
        } else //if filter empty, return all products.
            return arr;
    }
}
