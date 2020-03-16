drop database aurora;
create database aurora collate latin1_swedish_ci;

use aurora;

create or replace table application_user
(
    id         bigint       not null        primary key,
    password varchar(255)                    not null,
    role     varchar(255) default 'ADMIN'    not null,
    username varchar(255) default 'username' not null
);

create or replace table farms
(
    id         bigint       not null        primary key,
    created_at datetime     not null,
    updated_at datetime     not null,
    code       varchar(255) not null,
    name       varchar(255) not null,
    constraint UK_3214ianrcn3ndqcuta6d7nq2f
        unique (code),
    constraint UK_5eca4s3pnujm1syymfk36pkgu
        unique (name)
);

create or replace table entities
(
    id         bigint       not null        primary key,
    created_at datetime     not null,
    updated_at datetime     not null,
    name       varchar(255) not null,
    code       varchar(255) not null,
    farm_id    bigint       not null,
    constraint FKmviauev05fby66gs0430l5w6
        foreign key (farm_id) references farms (id)
);

create or replace table entity_entries
(
    id         bigint       not null        primary key,
    created_at  datetime     not null,
    updated_at  datetime     not null,
    description varchar(255) not null,
    name        varchar(255) not null,
    public_name varchar(255) not null,
    type        int          not null,
    entity_id   bigint       not null,
    farm_id     bigint       not null,
    constraint FKbdc35xvfg95dj1gd87p2gdoh7
        foreign key (entity_id) references entities (id),
    constraint FXtqgobn3gl30r0fb4wtn659s1l
        foreign key (farm_id) references farms (id)
);

create or replace table hibernate_sequence
(
    next_val bigint null
);

create or replace table table_configs
(
    id         bigint       not null        primary key,
    created_at         datetime     not null,
    updated_at         datetime     not null,
    display_pagination bit          not null,
    entity_id          bigint       not null,
    constraint UK_h5yns1yartfn5kp90jqp31385
        foreign key (entity_id) references entities (id)
);

create or replace table config_entry_rel
(
    config_id       bigint not null,
    entity_entry_id bigint not null,
    primary key (config_id, entity_entry_id),
    constraint FK78rowxy1suo52csr34vokd13r
        foreign key (entity_entry_id) references entity_entries (id),
    constraint FKtqgobn3gl30r0fb4wtn659s1a
        foreign key (config_id) references table_configs (id)
);
