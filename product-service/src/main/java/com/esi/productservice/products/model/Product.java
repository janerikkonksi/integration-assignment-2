package com.esi.productservice.products.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producttable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Product {
    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;

public Product(String id) {
        this.id = id;
    }

}