CREATE TABLE department
(
    id   integer auto_increment not null,
    name varchar(255) not null unique,
    primary key (id)
);

CREATE TABLE employee
(
    id     integer auto_increment not null,
    name   varchar(255)   not null unique,
    salary numeric(19, 2) not null,
    employee_department integer not null,
    primary key (id)
);

ALTER TABLE employee
ADD CONSTRAINT fk_employee_department FOREIGN KEY (employee_department) REFERENCES department (id) ON DELETE CASCADE;
