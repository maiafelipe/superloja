CREATE TABLE usuario(
    id bigint not null auto_increment,
    username varchar(150) not null,
    password varchar(64) not null,
    role varchar(45) not null,
    PRIMARY KEY(id)
);