
-- V2__create_bases_table.sql
-- Migration #2: create table bases

-- Creating table 'bases' for saving main data of bases
CREATE TABLE bases
(
    id             bigint auto_increment primary key,
    title          varchar(255) null,
    description    text null,
    created_at     datetime(6)  null,
    updated_at     datetime(6)  null
);

