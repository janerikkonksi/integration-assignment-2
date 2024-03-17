package ee.ut.esi.customers.customers.dto;

import lombok.Data;

@Data
public class NewCustomerDTO {
    private String name;
    private String email;
    private String phone;
    private String address;
}
