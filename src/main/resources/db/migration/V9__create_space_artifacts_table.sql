
-- V9__create_space_artifacts_table.sql
-- Migration #9: create table space_artifacts

-- Creating table 'space_artifacts' for saving main data of relation between space and artifacts
CREATE TABLE space_artifacts
(
    id                 bigint auto_increment primary key,
    space_id           bigint null,
    artifact_id        bigint null,

    created_at         datetime(6) null,
    updated_at         datetime(6) null
);

-- Adding index for fast searching by space_id
CREATE INDEX idx_space_artifacts_space_id ON space_artifacts(space_id);

-- Adding index for fast searching by artifact_id
CREATE INDEX idx_space_artifacts_artifact_id ON space_artifacts(artifact_id);

