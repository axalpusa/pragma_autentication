create table status
(
    id_status   SERIAL PRIMARY KEY,
    name        varchar(200),
    description varchar(200)
);
create table type_loan
(
    id_type_loan         SERIAL PRIMARY KEY,
    name                 varchar(200),
    minimum_amount       decimal(12, 2),
    maximum_amount       decimal(12, 2),
    interest_rate        decimal(12, 2),
    automatic_validation boolean default false
);

create table orders
(
    id_order      SERIAL PRIMARY KEY,
    mount         decimal(12, 2),
    term_months   integer,
    document_id   varchar(15),
    email_address varchar(200) UNIQUE,
    id_status     integer,
    id_type_loan  integer,
    FOREIGN KEY (id_status) REFERENCES status (id_status),
    FOREIGN KEY (id_type_loan) REFERENCES type_loan (id_type_loan)
);

---STATUS
INSERT INTO status (name, description)
VALUES ('Pendiente de revisión', 'Pendiente de revisión');
INSERT INTO status (name, description)
VALUES ('En revision', 'En revision');
---TYPE LOAN
INSERT INTO type_loan (name, minimum_amount,maximum_amount,interest_rate,automatic_validation)
VALUES ('Prestamo 1', 0,5000,0.45,true);
INSERT INTO type_loan (name, minimum_amount,maximum_amount,interest_rate,automatic_validation)
VALUES ('Prestamo 2', 3000,10000,0.60,false);