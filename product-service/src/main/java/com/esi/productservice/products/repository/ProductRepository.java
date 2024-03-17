package com.esi.productservice.products.repository;

import org.springframework.data.repository.CrudRepository;

import com.esi.productservice.products.model.Product;

public interface ProductRepository extends CrudRepository<Product, String>{

}
