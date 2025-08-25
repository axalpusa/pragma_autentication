package co.com.pragma.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class UserRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    String lastName;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    String emailAddress;

    String address;

    @NotBlank(message = "El documento de identidad es obligatorio")
    String documentId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate birthDate;
    String phoneNumber;

    @NotNull(message = "El salario base no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El salario no puede ser negativo")
    @DecimalMax(value = "15000000.0", inclusive = true, message = "El salario no puede superar 15M")
    BigDecimal baseSalary;

    //@NotBlank(message = "El rol es obligatorio")
    // Long idRol

}
