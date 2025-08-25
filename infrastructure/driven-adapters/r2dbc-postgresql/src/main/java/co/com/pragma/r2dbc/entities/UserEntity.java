package co.com.pragma.r2dbc.entities;

import co.com.pragma.model.rol.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Table(schema = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(name = "id_user")
    private Long idUSer;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "address")
    private String address;

    @Column(name = "document_id")
    private String documentId;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "base_salary")
    private BigDecimal baseSalary;

   // @Column(name = "id_rol")
  //  private Rol rol;

}
