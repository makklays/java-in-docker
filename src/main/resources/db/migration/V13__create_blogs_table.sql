
-- V13__create_blogs_table.sql
-- Migration #13: create table blogs

-- Creating table 'blogs' for saving main data of blog
CREATE TABLE blogs
(
    id                 bigint auto_increment primary key,

    space_id           bigint null,
    sector             bigint null,

    day                bigint null,
    action             varchar(255) null,

    title              varchar(255) null,
    post               text null,
    author             varchar(255) null,

    created_at         datetime(6) null
);

-- Adding index for fast searching by title
CREATE INDEX idx_blogs_title ON blogs(title);
CREATE INDEX idx_blogs_action ON blogs(action);

