package co.com.pragma.model.user;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private Long idUSer;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String documentId;
    private Timestamp birthDate;
    private String phoneNumber;
    private BigDecimal baseSalary;
    //  private Rol rol;

}
