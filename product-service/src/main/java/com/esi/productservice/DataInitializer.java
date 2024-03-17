package com.esi.productservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.esi.productservice.products.model.Product;
import com.esi.productservice.products.repository.ProductRepository;

import java.math.BigDecimal;


@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadProductsData(ProductRepository productRepository) {
        return args -> {
            Product product1 = new Product();
			product1.setId("01");
            product1.setCode("p-109-09");
			product1.setName("light vehicle");
			product1.setDescription("Can also be used for light work");
			product1.setPrice(BigDecimal.valueOf(1233));

			productRepository.save(product1);

			Product product2 = new Product();
			product2.setId("02");
            product2.setCode("p-109-10");
			product2.setName("new light vehicle");
			product2.setDescription("Can also be used for light work");
			product2.setPrice(BigDecimal.valueOf(1233));

			productRepository.save(product2);
        };
    }
}