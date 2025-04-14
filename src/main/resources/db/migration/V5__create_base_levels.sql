
CREATE TABLE base_levels
(
    id                 bigint auto_increment primary key,
    base_id            bigint not null,
    level              int not null,
    title              varchar(255) null,
    description        text null,
    res_agua           int null,
    res_plastic        int null,
    res_food           int null,
    res_iron           int null,
    plus_res_agua      int null,
    plus_res_plastic   int null,
    plus_res_food      int null,
    plus_res_iron      int null,
    build_seconds      int null,
    img                varchar(255) null,
    created_at         datetime(6)  null,
    updated_at         datetime(6)  null
);

