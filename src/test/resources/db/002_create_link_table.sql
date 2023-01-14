create table link
(
    id         serial primary key  not null,
    url        varchar(255) unique not null,
    account_id int                 not null references account (id)
);