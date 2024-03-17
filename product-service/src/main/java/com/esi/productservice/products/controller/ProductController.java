package com.esi.productservice.products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.productservice.products.dto.ProductDto;
import com.esi.productservice.products.dto.ProductQuantityDto;
import com.esi.productservice.products.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

@Autowired
private ProductService productService;


@GetMapping("/products")
public List<ProductDto> getAllProducts(){
return productService.getAllProducts();
}


@GetMapping("/products/{id}")
public Optional<ProductDto> getProduct(@PathVariable String id){
return productService.getProduct(id);
}

@GetMapping("/productquantity/{id}")
public Optional<ProductQuantityDto> getProductWithQuantity(@PathVariable String id){
return productService.getProductWithQuantity(id);
}


@PostMapping("/products")
public void addProduct(@RequestBody ProductDto productDto){
productService.addProduct(productDto);
}

@PutMapping("/products/{id}")
public void updateProduct(@RequestBody ProductDto productDto, @PathVariable String id){
productService.updateProduct(id, productDto);
}

@DeleteMapping("/products/{id}")
public void deleteProduct(@PathVariable String id){
productService.deleteProduct(id);
}
}
