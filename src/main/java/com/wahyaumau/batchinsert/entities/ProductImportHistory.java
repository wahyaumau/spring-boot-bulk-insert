package com.wahyaumau.batchinsert.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "product_import_history")
@NoArgsConstructor
@AllArgsConstructor
public class ProductImportHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startInsert;
    private LocalDateTime endInsert;
}
