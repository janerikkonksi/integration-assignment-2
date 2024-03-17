package com.esi.productservice.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.esi.productservice.products.dto.ProductDto;
import com.esi.productservice.products.dto.ProductQuantityDto;
import com.esi.productservice.products.model.Product;
import com.esi.productservice.products.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

@Autowired
private ProductRepository productRepository;

@Autowired
private WebClient.Builder webClientBuilder;

    public   List<ProductDto> getAllProducts(){
    List<Product> products =  new ArrayList<>();
    productRepository.findAll().forEach(products::add);
    return products.stream().map(this::mapToProductDto).toList();
    }    
    
        private ProductDto mapToProductDto(Product product) {
                return ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .code(product.getCode())
                        .build();
            }

            public   Optional<ProductDto>  getProduct(String id){
            Optional<Product> product =  productRepository.findById(id);
            return product.map(this::mapToProductDto);
        }
        
            
        public   Optional<ProductQuantityDto>  getProductWithQuantity(String id){
            Optional<Product> product =  productRepository.findById(id);
            return product.map(this::mapToProductQuantityDto);
        }

        private ProductQuantityDto mapToProductQuantityDto(Product product) {

            // Fetching the quantity from the inventory service using webClient
            Integer quantity = webClientBuilder 
            .build() // A mutable builder for creating a WebClient
            .get() // specifying the request method (GET)  
            .uri("http://localhost:8083/api/inventory/{code}", product.getCode()) 
            // .uri() specify the uri for the request 
            // product.getCode() is used for getting and passing the code of the product to be send to the inventory service
            .retrieve() //we called .retrieve() to get a response for the request
            .bodyToMono(Integer.class)
            /* 
            The method bodyToMono() extracts the body to a Mono instance. 
            We use bodyToMono() for single (0-1) returned items
            We use bodyToFlux() for multiple (0-N) returned items, we will cover bodyToFlux() later.
            */
            .block(); //The method .block() subscribes to this Mono instance and blocks until the response is received.
            
            return ProductQuantityDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .code(product.getCode())
                //here we are mapping the fetched quality to the ProductQuantityDto
                .quantity(quantity)
                .build();
        }

        public void addProduct(ProductDto productDto) {
            Product product = Product.builder()
            .id(productDto.getId())
            .name(productDto.getName())
            .description(productDto.getDescription())
            .price(productDto.getPrice())
            .code(productDto.getCode())
            .build();
        productRepository.save(product);
        log.info("Product {} is added to the Database", product.getId());
        }

        public void updateProduct(String id, ProductDto productDto) {
        Product product = Product.builder()
            .id(productDto.getId())
            .name(productDto.getName())
            .description(productDto.getDescription())
            .price(productDto.getPrice())
            .code(productDto.getCode())
            .build();
        productRepository.save(product);
        log.info("Product {} is updated", product.getId());
        }

        public void deleteProduct(String id) {
        productRepository.deleteById(id);
        log.info("A Product has been deleted");
        }
    };

