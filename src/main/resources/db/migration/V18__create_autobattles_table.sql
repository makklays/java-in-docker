
-- V18__create_autobattles_table.sql
-- Migration #18: create table autobattles

-- Creating table 'autobattles' for saving main data of auto battle
CREATE TABLE autobattles
(
    id                    bigint auto_increment primary key,
    title                 varchar(255) not null,

    req_building_level    bigint not null,
    req_units_count       bigint not null,
    req_res_iron          bigint not null,

    hp                    bigint not null,
    attack                bigint not null,
    armor                 bigint not null,

    reward_res_agua       bigint not null,
    reward_res_plastic    bigint not null,
    reward_res_food       bigint not null,
    reward_res_iron       bigint not null,

    created_at            datetime(6)  null,
    updated_at            datetime(6)  null
);

-- Adding index for fast searching by req_building_level
CREATE INDEX idx_ab_req_building_level ON autobattles(req_building_level);
-- Adding index for fast searching by req_units_count
CREATE INDEX idx_ab_req_units_count ON autobattles(req_units_count);
-- Adding index for fast searching by req_res_iron
CREATE INDEX idx_ab_req_res_iron ON autobattles(req_res_iron);

