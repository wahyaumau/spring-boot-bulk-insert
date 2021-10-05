package com.wahyaumau.batchinsert.controllers;

import com.wahyaumau.batchinsert.entities.Product;
import com.wahyaumau.batchinsert.entities.ProductImportHistory;
import com.wahyaumau.batchinsert.repositories.ProductImportHistoryRepository;
import com.wahyaumau.batchinsert.services.ProductService;
import com.wahyaumau.batchinsert.utils.ProductDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductImportHistoryRepository productImportHistoryRepository;

    @Autowired
    ProductService productService;

    @PostMapping
    public void saveAllProduct(){
        LocalDateTime startInsert = LocalDateTime.now();
        ProductImportHistory importHistory = ProductImportHistory
                .builder()
                .startInsert(startInsert)
                .build();
        productImportHistoryRepository.save(importHistory);
        List<Product> productData = ProductDataBuilder
                .builder()
                .setTotal(50_000)
                .setImportHistory(importHistory)
                .build();

        System.out.print("Using id identity, ");
        productService.saveAllJdbcBatchCallable(productData);
        LocalDateTime endInsert = LocalDateTime.now();
        importHistory.setEndInsert(endInsert);
        productImportHistoryRepository.save(importHistory);
        System.out.println(productData.size() + " data inserted, duration in second "+ ChronoUnit.SECONDS.between(startInsert, endInsert));
    }
}
