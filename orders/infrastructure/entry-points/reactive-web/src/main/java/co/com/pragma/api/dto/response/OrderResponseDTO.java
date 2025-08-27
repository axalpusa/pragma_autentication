package co.com.pragma.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    Long idOrder;
    BigDecimal amount;
    Integer termMonths;
    String documentId;
    String emailAddress;
    Integer idStatus;
    Integer idTypeLoan;
}
