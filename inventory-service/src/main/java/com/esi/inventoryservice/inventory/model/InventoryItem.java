package com.esi.inventoryservice.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventorytable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class InventoryItem {
    
    @Id
    private String id;
    private String code;
    private Integer quantity;
}