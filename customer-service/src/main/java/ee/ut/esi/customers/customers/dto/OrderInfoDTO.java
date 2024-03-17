package ee.ut.esi.customers.customers.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;


// Note: This should be somewhere common so there's only one source of truth
@Data
public class OrderInfoDTO {
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String status;
    private String shippingAddress;
    private String billingAddress;
    private String paymentMethod;
}