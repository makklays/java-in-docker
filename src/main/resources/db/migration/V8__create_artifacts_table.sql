
-- V8__create_artifacts_table.sql
-- Migration #8: create table artifacts

-- Creating table 'artifacts' for saving main data of artifact
CREATE TABLE artifacts
(
    id                 bigint auto_increment primary key,
    title              varchar(255) null,
    description        text null,

    amount             DECIMAL(15,2) NOT NULL,

    plus_res_agua      int(12) null,
    plus_res_plastic   int(12) null,
    plus_res_food      int(12) null,
    plus_res_iron      int(12) null,

    created_at         datetime(6) null,
    updated_at         datetime(6) null
);

-- Adding index for fast searching by title
CREATE INDEX idx_artifacts_title ON artifacts(title);

