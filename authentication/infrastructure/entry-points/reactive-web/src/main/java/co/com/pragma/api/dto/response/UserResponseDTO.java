package co.com.pragma.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {
    String idUser;
    String firstName;
    String lastName;
    String emailAddress;
    String address;
    String documentId;
    LocalDate birthDate;
    String phoneNumber;
    BigDecimal baseSalary;
    String idRol;
}
