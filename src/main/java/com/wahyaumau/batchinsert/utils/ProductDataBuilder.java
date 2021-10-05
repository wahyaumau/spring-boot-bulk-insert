package com.wahyaumau.batchinsert.utils;

import com.wahyaumau.batchinsert.entities.Product;
import com.wahyaumau.batchinsert.entities.ProductImportHistory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductDataBuilder {
    private final Random random = new Random();
    private ProductImportHistory importHistory;
    private int total;

    public static ProductDataBuilder builder(){
        return new ProductDataBuilder();
    }

    public ProductDataBuilder setImportHistory(ProductImportHistory history){
        importHistory = history;
        return this;
    }

    public ProductDataBuilder setTotal(int total){
        this.total = total;
        return this;
    }
    
    public List<Product> build(){
        return IntStream.range(0, total)
                .mapToObj(val -> Product.builder()
                        .title("Product " + val)
                        .stock(random.nextInt(total))
                        .price((float) (random.nextInt(total) * 0.5))
                        .importHistory(importHistory)
                        .build()
                ).collect(Collectors.toList());
    }
}
