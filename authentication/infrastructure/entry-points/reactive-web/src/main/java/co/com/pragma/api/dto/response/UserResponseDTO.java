package co.com.pragma.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    Long idUser;
    String firstName;
    String lastName;
    String emailAddress;
    String address;
    String documentId;
    LocalDate birthDate;
    String phoneNumber;
    BigDecimal baseSalary;
    Integer idRol;
}
