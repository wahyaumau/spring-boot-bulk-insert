package com.wahyaumau.batchinsert.repositories;

import com.wahyaumau.batchinsert.entities.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Integer> {
}
