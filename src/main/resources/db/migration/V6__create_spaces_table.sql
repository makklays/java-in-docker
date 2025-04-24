
-- V6__create_spaces_table.sql
-- Migration #6: create table spaces

-- Creating table 'spaces' for saving main data of space
CREATE TABLE spaces
(
    id             bigint auto_increment primary key,
    user_id        bigint null,
    title          varchar(255) null,

    res_agua       int(12) null,
    res_plastic    int(12) null,
    res_food       int(12) null,
    res_iron       int(12) null,

    do_res_agua    int(12) null,
    do_res_plastic int(12) null,
    do_res_food    int(12) null,
    do_res_iron    int(12) null,

    created_at     datetime(6) null,
    updated_at     datetime(6) null
);

-- Adding index for fast searching by user_id
CREATE INDEX idx_spaces_user_id ON spaces(user_id);

-- Adding index for fast searching by title
CREATE INDEX idx_spaces_title ON spaces(title);

