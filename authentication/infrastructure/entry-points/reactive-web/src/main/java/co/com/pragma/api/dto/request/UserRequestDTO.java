package co.com.pragma.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class UserRequestDTO {

    @NotBlank(message = "First name is required")
    String firstName;

    @NotBlank(message = "Last name is required")
    String lastName;

    @NotBlank(message = "Email address is required")
    @Email(message = "El correo electrónico no tiene un formato válido")
    String emailAddress;

    String address;

    @NotBlank(message = "Document id is required")
    String documentId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate birthDate;
    String phoneNumber;

    @NotNull(message = "Base salary is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "The salary cannot be negative")
    @DecimalMax(value = "15000000.0", inclusive = true, message = "The salary cannot be 15M")
    BigDecimal baseSalary;

    @NotNull(message = "Rol is required")
    Integer idRol;

}
