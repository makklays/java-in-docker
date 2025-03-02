
CREATE TABLE bases
(
    id          bigint auto_increment primary key,
    title       varchar(255) null,
    description text null,
    counts      bigint null,
    latitude    varchar(255) null,
    longitude   varchar(255) null,
    is_store    ENUM('1', '0') NOT NULL DEFAULT '0',
    is_air      ENUM('1', '0') NOT NULL DEFAULT '0',
    is_earth    ENUM('1', '0') NOT NULL DEFAULT '0',
    is_see      ENUM('1', '0') NOT NULL DEFAULT '0',
    is_fun      ENUM('1', '0') NOT NULL DEFAULT '0',
    is_guard    ENUM('1', '0') NOT NULL DEFAULT '0',

    status      ENUM('active', 'inactive', 'new', 'old', 'building', 'banned') NOT NULL DEFAULT 'inactive',

    created_at  datetime(6)  null,
    updated_at  datetime(6)  null
);

