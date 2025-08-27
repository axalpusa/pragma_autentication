package co.com.pragma.api.dto.response;

import java.math.BigDecimal;

public class TypeLoanResponseDTO {
    Long idTypeLoan;
    String name;
    BigDecimal minimumAmount;
    BigDecimal maximumAmount;
    BigDecimal interestRate;
    String automaticValidation;
}
