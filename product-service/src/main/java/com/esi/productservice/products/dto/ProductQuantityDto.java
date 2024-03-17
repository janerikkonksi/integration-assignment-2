package com.esi.productservice.products.dto;

import java.math.BigDecimal;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class ProductQuantityDto {
    
    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;


public ProductQuantityDto(String id) {
        this.id = id;
    }

}