package com.wahyaumau.batchinsert.repositories;

import com.wahyaumau.batchinsert.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
