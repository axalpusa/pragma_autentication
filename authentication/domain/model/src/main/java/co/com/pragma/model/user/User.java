package co.com.pragma.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * User model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String documentId;
    private LocalDate birthDate;
    private String phoneNumber;
    private BigDecimal baseSalary;
    private Integer idRol;

}
