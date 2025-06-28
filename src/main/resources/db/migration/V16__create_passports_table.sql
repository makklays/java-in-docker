
-- V16__create_passports_table.sql
-- Migration #16: create table passports

-- Creating table 'passports' for saving main data of passport
CREATE TABLE passports
(
    id                    bigint auto_increment primary key,
    user_id               bigint not null,
    number                varchar(255) not null,

    date_at               datetime(6)  null,
    who_gave_it           varchar(255) null,

    created_at            datetime(6)  null,
    updated_at            datetime(6)  null
);

-- Adding index for fast searching by user_id
CREATE INDEX idx_passports_user_id ON passports(user_id);

-- Adding index for fast searching by number
CREATE INDEX idx_passports_number ON passports(number);

