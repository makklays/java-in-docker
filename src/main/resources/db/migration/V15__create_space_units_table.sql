
-- V15__create_space_units_table.sql
-- Migration #15: create table space units

-- Creating table 'space_units' for relation between space and units (on table space_units)
CREATE TABLE space_units
(
    id                    bigint auto_increment primary key,
    space_id              bigint not null,
    unit_id               bigint not null,

    training_started_at   datetime(6)  null,
    is_training           ENUM('0', '1') NOT NULL DEFAULT '0',
    count                 int(12) null,

    created_at            datetime(6)  null,
    updated_at            datetime(6)  null
);

-- Adding index for fast searching by space_id
CREATE INDEX idx_space_units_space_id ON space_units(space_id);

-- Adding index for fast searching by unit_id
CREATE INDEX idx_space_units_unit_id ON space_units(unit_id);

