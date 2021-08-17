CREATE TABLE audit
(
    id   integer auto_increment not null,
    action_type varchar(255) not null,
    primary key (id)
);