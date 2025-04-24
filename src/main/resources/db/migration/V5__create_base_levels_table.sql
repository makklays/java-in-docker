
-- V5__create_base_levels_table.sql
-- Migration #5: create table base_levels

-- Creating table 'base_levels' for saving main data of levels of base
CREATE TABLE base_levels
(
    id                 bigint auto_increment primary key,
    base_id            bigint not null,
    level              int not null,

    title              varchar(255) null,
    description        text null,

    res_agua           int null,
    res_plastic        int null,
    res_food           int null,
    res_iron           int null,

    plus_res_agua      int null,
    plus_res_plastic   int null,
    plus_res_food      int null,
    plus_res_iron      int null,

    build_seconds      int null,
    img                varchar(255) null,

    created_at         datetime(6)  null,
    updated_at         datetime(6)  null
);

-- Adding index for fast searching by base_id
CREATE INDEX idx_base_levels_base_id ON base_levels(base_id);

