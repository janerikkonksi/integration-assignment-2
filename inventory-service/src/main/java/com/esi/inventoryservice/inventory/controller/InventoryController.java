package com.esi.inventoryservice.inventory.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.esi.inventoryservice.inventory.service.InventoryService;



@RestController
public class InventoryController {

@Autowired
private InventoryService inventoryService;


@GetMapping("/api/inventory/{code}")
    public Integer quantityInStock(@PathVariable String code) {
        return inventoryService.quantityInStock(code);
    }
}
