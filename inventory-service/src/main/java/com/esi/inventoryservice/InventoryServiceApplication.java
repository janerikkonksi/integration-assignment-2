package com.esi.inventoryservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("inventoryservice_db");
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
}
