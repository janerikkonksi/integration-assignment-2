package ee.ut.esi.customers.customers.service;

import ee.ut.esi.customers.customers.dto.CustomerDTO;
import ee.ut.esi.customers.customers.dto.NewCustomerDTO;
import ee.ut.esi.customers.customers.model.Customer;
import ee.ut.esi.customers.customers.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    public CustomerService(final CustomerRepository customerRepository,
                           final ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
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
        return customer.map(c -> modelMapper.map(c, CustomerDTO.class));
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
