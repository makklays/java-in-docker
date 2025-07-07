
-- V20__create_space_autobattle_steps_table.sql
-- Migration #20: create table space_autobattle_steps

-- Creating table 'space_autobattle_steps' for relation between space_autobattle and steps of autobattle (on table space_autobattle_steps)
CREATE TABLE space_autobattle_steps
(
    id                    bigint auto_increment primary key,
    space_autobattle_id   bigint not null,

    step                  bigint not null,
    attacker_name         varchar(255) not null,
    defender_name         varchar(255) not null,

    attack_value          bigint not null,
    armor_value           bigint not null,
    damage_value          bigint not null,
    defender_hp_after_hit bigint not null,

    is_critical           ENUM('1', '0') NOT NULL DEFAULT '0',

    log_message           text null,

    created_at            datetime(6)  null,
    updated_at            datetime(6)  null
);

-- Adding index for fast searching by space_autobattle_id
CREATE INDEX idx_sas_space_autobattle_id ON space_autobattle_steps(space_autobattle_id);

-- Adding index for fast searching by attacker_name
CREATE INDEX idx_sas_attacker_name ON space_autobattle_steps(attacker_name);
-- Adding index for fast searching by defender_name
CREATE INDEX idx_sas_defender_name ON space_autobattle_steps(defender_name);

