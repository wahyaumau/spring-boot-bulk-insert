package com.wahyaumau.batchinsert.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "products_id_seq", allocationSize = 1)
    private int id;
    private String title;
    private Integer stock;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "import_history_id")
    private ProductImportHistory importHistory;
}
