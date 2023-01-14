create table link_shortcut
(
    id      serial primary key  not null,
    calls   int                 not null,
    code    varchar(255) unique not null,
    link_id int                 not null references link (id)
);