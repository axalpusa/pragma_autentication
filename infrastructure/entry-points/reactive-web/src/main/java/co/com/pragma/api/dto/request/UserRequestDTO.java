package co.com.pragma.api.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record UserRequestDTO(
        @NotBlank(message = "El nombre es obligatorioo")
        String firstName,

        @NotBlank(message = "El apellido es obligatorio")
        String lastName,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico no tiene un formato válido")
        String emailAddress,

        String address,

        @NotBlank(message = "El documento de identidad es obligatorio")
        String documentId,

        Timestamp birthDate, String phoneNumber,

        @NotNull(message = "El salario base no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = true, message = "El salario no puede ser negativo")
        @DecimalMax(value = "15000000.0", inclusive = true, message = "El salario no puede superar 15M")
        BigDecimal baseSalary,

        @NotBlank(message = "El rol es obligatorio")
        Long idRol
) {


}
