
-- V7__create_maps_table.sql
-- Migration #7: create table maps

-- Creating table 'maps' for saving main data of map
CREATE TABLE maps
(
    id             bigint auto_increment primary key,
    space_id       bigint null,
    sector         bigint null,
    base_level_id  bigint null,

    created_at     datetime(6) null,
    updated_at     datetime(6) null
);

-- Adding index for fast searching by space_id
CREATE INDEX idx_maps_space_id ON maps(space_id);

-- Adding index for fast searching by sector
CREATE INDEX idx_maps_sector ON maps(sector);

-- Adding index for fast searching by base_level_id
CREATE INDEX idx_maps_base_level_id ON maps(base_level_id);

