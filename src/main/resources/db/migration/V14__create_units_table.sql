
-- V14__create_units_table.sql
-- Migration #14: create table units

-- Creating table 'units' for saving main data of unit
CREATE TABLE units
(
    id                 bigint auto_increment primary key,
    title              varchar(255) null,
    description        text null,
    type               ENUM('biolab', 'hangar') NOT NULL DEFAULT 'biolab',

    res_agua           int(12) null,
    res_plastic        int(12) null,
    res_food           int(12) null,
    res_iron           int(12) null,
    level              int not null,

    hp                 int(12) null,
    armor              int(12) null,
    attack             int(12) null,
    `range`            int(12) null,
    speed              int(12) null,

    training_seconds   int null,
    img                varchar(255) null,

    created_at         datetime(6)  null,
    updated_at         datetime(6)  null
);

-- Adding index for fast searching by title and type
CREATE INDEX idx_units_title ON units(title);
CREATE INDEX idx_units_type ON units(type);
CREATE INDEX idx_units_level ON units(level);

