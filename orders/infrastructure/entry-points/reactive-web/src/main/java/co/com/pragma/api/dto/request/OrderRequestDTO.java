package co.com.pragma.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderRequestDTO {

    @NotNull(message = "Mount is required")
    BigDecimal mount;

    @NotNull(message = "Term months is required")
    @Positive(message = "The term months must be greater than 0")
    Integer termMonths;

    @NotBlank(message = "Document id is required")
    String documentId;

    @NotBlank(message = "Email address is required")
    String emailAddress;

    @NotNull(message = "Type loan is required")
    Integer idTypeLoan;
}
