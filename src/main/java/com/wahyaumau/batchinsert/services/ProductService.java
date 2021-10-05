package com.wahyaumau.batchinsert.services;

import com.wahyaumau.batchinsert.entities.Product;
import com.wahyaumau.batchinsert.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    public void saveAll(List<Product> productData){
        System.out.println("insert using saveAll, hibernate batch");
        productRepository.saveAll(productData);
    }

    public void saveAllHibernateBatch(List<Product> productData){
        System.out.println("insert using saveAll, hibernate + manual batch");
        List<Product> productDataTemp = new ArrayList<>();
        int counter = 0;
        for(Product product: productData){
            productDataTemp.add(product);
            if((counter + 1) % batchSize == 0 || (counter + 1) == productData.size()){
                productRepository.saveAll(productDataTemp);
                productDataTemp.clear();
            }
            counter++;
        }
    }
}
