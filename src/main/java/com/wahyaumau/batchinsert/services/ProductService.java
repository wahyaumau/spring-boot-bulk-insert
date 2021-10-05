package com.wahyaumau.batchinsert.services;

import com.wahyaumau.batchinsert.entities.Product;
import com.wahyaumau.batchinsert.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void saveAll(List<Product> productData){
        System.out.println("insert using saveAll");
        productRepository.saveAll(productData);
    }
}
