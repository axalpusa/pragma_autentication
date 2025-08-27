create table rol
(
    id_rol      SERIAL PRIMARY KEY,
    name       varchar(200),
    desciption varchar(200)
);
create table users
(
    id_user       SERIAL PRIMARY KEY,
    first_name    varchar(200),
    last_name     varchar(200),
    email_address varchar(200) UNIQUE,
    address       varchar(200),
    document_id   varchar(15),
    birth_date     date  ,
    phone_number  varchar(15),
    base_salary   decimal(12, 2),
    id_rol        integer,
    FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
);

INSERT INTO rol (name, desciption)
VALUES ('rol 1', 'descripcion rol 1');

INSERT INTO rol (name, desciption)
VALUES ('rol 2', 'descripcion rol 2');