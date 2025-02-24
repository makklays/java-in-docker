
CREATE TABLE users
(
    id          bigint auto_increment primary key,
    username    varchar(255) null,
    email       varchar(200) not null,
    password    varchar(255) null,
    created_at  datetime(6)  null,
    updated_at  datetime(6)  null,

    constraint UK_email unique (email),
    constraint UK_username unique (username)
);

