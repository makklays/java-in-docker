
-- V3__create_bases_table.sql
-- Migration #3: create table contact

-- Creating table 'contact' for saving main data of contact from users
CREATE TABLE contacts
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

-- Adding index for fast searching by email
CREATE INDEX idx_contacts_email ON contacts(email);

