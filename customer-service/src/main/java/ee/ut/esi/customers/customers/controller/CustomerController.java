package ee.ut.esi.customers.customers.controller;

import ee.ut.esi.customers.customers.dto.CustomerDTO;
import ee.ut.esi.customers.customers.dto.NewCustomerDTO;
import ee.ut.esi.customers.customers.service.CustomerService;
import ee.ut.esi.customers.exceptions.NotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        var customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody NewCustomerDTO newCustomer) {
        var customer = customerService.addCustomer(newCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        var customer = customerService.getCustomer(id)
            .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id,
                                                      @RequestBody NewCustomerDTO newCustomer) {
        var customer = customerService.updateCustomer(id, newCustomer)
            .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
