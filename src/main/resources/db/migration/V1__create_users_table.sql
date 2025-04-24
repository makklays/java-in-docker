
-- V1__create_users_table.sql
-- Migration #1: create table users

-- Creating table 'users' for saving main data of users
CREATE TABLE users
(
    id          bigint auto_increment primary key,
    username    varchar(255) null,
    email       varchar(200) not null,
    mob         varchar(255) null,

    gender      varchar(255) null,
    age         varchar(255) null,
    avatar      varchar(255) null,

    password    varchar(255) null,
    created_at  datetime(6)  null,
    updated_at  datetime(6)  null,

    constraint UK_email unique (email),
    constraint UK_username unique (username)
);

-- Adding index for fast searching by email
CREATE INDEX idx_users_email ON users(email);

-- Adding index for fast searching by mobile
CREATE INDEX idx_users_mob ON users(mob);

