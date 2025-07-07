
-- V19__create_space_autobattles_table.sql
-- Migration #19: create table space_autobattles

-- Creating table 'space_autobattles' for relation between space and autobattles (on table space_autobattles)
CREATE TABLE space_autobattles
(
    id                    bigint auto_increment primary key,
    space_id              bigint not null,
    autobattle_id         bigint not null,

    start_time            datetime(6)  null,
    end_time              datetime(6)  null,

    status                ENUM('WAITING', 'IN_PROGRESS', 'FINISHED') NOT NULL DEFAULT 'FINISHED',
    result                ENUM('WIN', 'LOSE', 'DRAW') NOT NULL DEFAULT 'LOSE',
    logs                  text null,

    created_at            datetime(6)  null,
    updated_at            datetime(6)  null
);

-- Adding index for fast searching by space_id
CREATE INDEX idx_sa_space_id ON space_autobattles(space_id);
-- Adding index for fast searching by autobattle_id
CREATE INDEX idx_sa_autobattle_id ON space_autobattles(autobattle_id);

-- Adding index for fast searching by status
CREATE INDEX idx_sa_status ON space_autobattles(status);
-- Adding index for fast searching by result
CREATE INDEX idx_sa_result ON space_autobattles(result);

