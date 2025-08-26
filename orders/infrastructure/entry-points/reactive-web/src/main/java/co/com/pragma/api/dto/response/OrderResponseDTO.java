package co.com.pragma.api.dto.response;

import java.math.BigDecimal;

public class OrderResponseDTO {
    Long idOrder;
    BigDecimal mount;
    Integer termMonths;
    String documentId;
    String emailAddress;
    Integer idStatus;
    Integer idTypeLoan;
}
