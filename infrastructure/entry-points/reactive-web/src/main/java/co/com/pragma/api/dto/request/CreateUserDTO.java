package co.com.pragma.api.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record CreateUserDTO(
        @NotBlank(message = "El nombre no puede estar vacio")
        String firstName,
        @NotBlank(message = "El apellido no puede estar vacio")
        String lastName,
        @NotBlank(message = "El correo electrónico no puede estar vacío")
        @Email(message = "El correo electrónico no tiene un formato válido")
        String emailAddress,
        String address,
        String documentId,
        Timestamp birthDate, String phoneNumber,
        @NotNull(message = "El salario base no puede ser nulo")
        @Min(value = 0, message = "El salario no puede ser negativo")
        @Max(value = 15000000, message = "El salario no puede superar 15M")
        BigDecimal baseSalary//,
        //Rol rol
) {


}
