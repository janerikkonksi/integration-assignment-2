package com.esi.inventoryservice.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.esi.inventoryservice.inventory.model.InventoryItem;


public interface InventoryRepository extends CrudRepository<InventoryItem, String>{

    InventoryItem findByCode(String code);

}
