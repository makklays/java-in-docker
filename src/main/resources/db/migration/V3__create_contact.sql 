
CREATE TABLE contact
(
    id            bigint auto_increment primary key,
    name          varchar(255) null,
    surname       varchar(255) null,
    email         varchar(200) not null,
    description   varchar(700) null,
    created_at    datetime(6)  null,

    constraint    UK_email unique (email),
    constraint    UK_username unique (name)
);

