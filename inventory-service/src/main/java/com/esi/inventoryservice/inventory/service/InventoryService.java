package com.esi.inventoryservice.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esi.inventoryservice.inventory.model.InventoryItem;
import com.esi.inventoryservice.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

@Autowired
private InventoryRepository inventoryRepository;


public   Integer quantityInStock(String code){
    InventoryItem inventoryItem = inventoryRepository.findByCode(code);
    return inventoryItem.getQuantity();
}    

};

