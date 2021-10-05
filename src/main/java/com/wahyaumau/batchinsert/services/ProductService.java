package com.wahyaumau.batchinsert.services;

import com.wahyaumau.batchinsert.entities.Product;
import com.wahyaumau.batchinsert.repositories.ProductRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    HikariDataSource hikariDataSource;

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

    public void saveAllJdbcBatch(List<Product> productData){
        System.out.println("insert using jdbc batch");
        String sql = String.format("INSERT INTO %s (title, stock, price, import_history_id) VALUES (?, ?, ?, ?)", Product.class.getAnnotation(Table.class).name());
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ){
            int counter = 0;
            for (Product product : productData) {
                statement.clearParameters();
                statement.setString(1, product.getTitle());
                statement.setInt(2, product.getStock());
                statement.setFloat(3, product.getPrice());
                statement.setInt(4, product.getImportHistory().getId());
                statement.addBatch();
                if ((counter + 1) % batchSize == 0 || (counter + 1) == productData.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
