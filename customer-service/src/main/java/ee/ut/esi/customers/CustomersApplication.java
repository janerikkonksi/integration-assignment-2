package ee.ut.esi.customers;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CustomersApplication {

    public static void main(String[] args) {
        DatabaseInitializer.initialize("customers");
        SpringApplication.run(CustomersApplication.class, args);
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    // DTO mapper
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
