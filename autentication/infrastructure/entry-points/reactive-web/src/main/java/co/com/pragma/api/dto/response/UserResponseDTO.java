package co.com.pragma.api.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.Data;

@Data
public class UserResponseDTO{
        Long idUser;
        String firstName;
        String lastName;
        String emailAddress;
        String address;
        String documentId;
        LocalDate birthDate;
        String phoneNumber;
        BigDecimal baseSalary;
        //Long idRol
}
