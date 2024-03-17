package com.esi.inventoryservice.inventory.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class InventoryItemDto {
    
    @Id
    private String id;
    private String code;
    private Integer quantity;
}