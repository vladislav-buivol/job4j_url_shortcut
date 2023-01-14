create table account
(
    id       serial primary key  not null,
    login    varchar(255) unique not null,
    password varchar(255)
);