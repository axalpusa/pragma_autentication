package co.com.pragma.r2dbc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @Column("id_user")
    private Long idUser;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("email_address")
    private String emailAddress;

    @Column("address")
    private String address;

    @Column("document_id")
    private String documentId;

    @Column("birth_date")
    private LocalDate birthDate;

    @Column("phone_number")
    private String phoneNumber;

    @Column("base_salary")
    private BigDecimal baseSalary;

    @Column("id_rol")
    private Integer idRol;
}
