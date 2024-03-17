package ee.ut.esi.customers.customers.dto;

import java.util.List;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    private List<OrderInfoDTO> orders;
}
