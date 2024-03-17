package ee.ut.esi.customers.customers.service;

import ee.ut.esi.customers.customers.dto.CustomerDTO;
import ee.ut.esi.customers.customers.dto.NewCustomerDTO;
import ee.ut.esi.customers.customers.dto.OrderInfoDTO;
import ee.ut.esi.customers.customers.model.Customer;
import ee.ut.esi.customers.customers.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class CustomerService {
    private static final String ORDER_SERVICE_URL = "http://localhost:8083/api/";

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    private final WebClient.Builder webClientBuilder;

    public CustomerService(final CustomerRepository customerRepository,
                           final ModelMapper modelMapper,
                           final WebClient.Builder webClientBuilder) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.webClientBuilder = webClientBuilder;
    }

    public List<CustomerDTO> getAllCustomers() {
        var customers = customerRepository.findAll();
        return customers.stream()
            .map(customer -> modelMapper.map(customer, CustomerDTO.class)).toList();
    }

    public CustomerDTO addCustomer(NewCustomerDTO customer) {
        var customerEntity = modelMapper.map(customer, Customer.class);
        var createdEntity = customerRepository.save(customerEntity);
        return modelMapper.map(createdEntity, CustomerDTO.class);
    }

    public Optional<CustomerDTO> getCustomer(Long id) {
        var customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            return Optional.empty();
        }

        var orderObjects = webClientBuilder
            .build()
            .get()
            .uri(ORDER_SERVICE_URL + "customer-orders/{customerId}", id)
            .retrieve()
            .bodyToMono(Object[].class)
            .block();

        List<OrderInfoDTO> orders;
        if (orderObjects != null) {
            orders = Stream.of(orderObjects)
                .map(order -> modelMapper.map(order, OrderInfoDTO.class))
                .toList();
        } else {
            orders = new ArrayList<>();
        }

        var customerDto = customer.map(c -> modelMapper.map(c, CustomerDTO.class));
        customerDto.ifPresent(c -> c.setOrders(orders));
        return customerDto;
    }

    public Optional<CustomerDTO> updateCustomer(Long id, NewCustomerDTO newCustomer) {
        var customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            return Optional.empty();
        }
        var customerEntity = customer.get();
        modelMapper.map(newCustomer, customerEntity);
        var updatedEntity = customerRepository.save(customerEntity);
        return Optional.of(modelMapper.map(updatedEntity, CustomerDTO.class));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
