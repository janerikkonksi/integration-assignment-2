package ee.ut.esi.customers.customers.repository;

import ee.ut.esi.customers.customers.model.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();
}
