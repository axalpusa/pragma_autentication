package co.com.pragma.api.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record UserResponseDTO(
        Long idUser,
        String firstName,
        String lastName,
        String emailAddress,
        String address,
        String documentId,
        Timestamp birthDate, String phoneNumber,
        BigDecimal baseSalary,
        Long idRol
) {
}
