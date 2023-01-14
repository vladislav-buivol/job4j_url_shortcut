create table website
(
    id      serial primary key  not null,
    site    varchar(255) unique not null,
    user_id int                 not null references account (id)
);