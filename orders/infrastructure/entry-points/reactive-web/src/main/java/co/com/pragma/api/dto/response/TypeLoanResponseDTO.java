package co.com.pragma.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeLoanResponseDTO {
    Long idTypeLoan;
    String name;
    BigDecimal minimumAmount;
    BigDecimal maximumAmount;
    BigDecimal interestRate;
    Boolean automaticValidation;
}
