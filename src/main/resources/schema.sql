drop database aurora;
create database aurora collate latin1_swedish_ci;
use aurora;

create or replace table application_user
(
    id bigint not null
        primary key,
    password varchar(255) not null,
    role varchar(255) default 'ADMIN' not null,
    username varchar(255) default 'username' not null
);

create or replace table farms
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    code varchar(255) not null,
    name varchar(255) not null,
    constraint UK_3214ianrcn3ndqcuta6d7nq2f
        unique (code),
    constraint UK_5eca4s3pnujm1syymfk36pkgu
        unique (name)
);

create or replace table entities
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    name varchar(255) not null,
    code varchar(255) not null,
    farm_id bigint not null,
    constraint FKmviauev05fby66gs0430l5w6
        foreign key (farm_id) references farms (id)
);

create or replace table entity_actions
(
    id bigint not null
        primary key,
    code varchar(255) not null,
    created_at datetime not null,
    updated_at datetime not null,
    entity_id bigint not null,
    existent boolean null,
    multiple boolean null,
    text varchar(255) not null,
    rules varchar(255) not null,
    constraint UK_qmhvxxxqrhi8kk56yx35u3xuo
        unique (code),
    constraint FKame9w0w4wbgpnvg95fwr66o3q
        foreign key (entity_id) references entities (id)
);

create or replace table entity_entries
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    description varchar(255) not null,
    code varchar(255) not null,
    name varchar(255) not null,
    public_name varchar(255) not null,
    type int not null,
    entity_id bigint not null,
    farm_id bigint not null,
    constraint FKbdc35xvfg95dj1gd87p2gdoh7
        foreign key (entity_id) references entities (id),
    constraint FXtqgobn3gl30r0fb4wtn659s1l
        foreign key (farm_id) references farms (id)
);

create or replace table form_configs
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    code varchar(255) not null,
    entity_id bigint not null,
    constraint FKaj9lii25xpg2xgo69hqtq8h9p
        foreign key (entity_id) references entities (id)
);

create or replace table form_entry_rel
(
    config_id bigint not null,
    entity_entry_id bigint not null,
    primary key (config_id, entity_entry_id),
    constraint FK48jh2nwnqwavsh3gb4y2om290
        foreign key (entity_entry_id) references entity_entries (id),
    constraint FKjjpmh7gy4o0ro2sfg7ubuwkn1
        foreign key (config_id) references form_configs (id)
);

create or replace table hibernate_sequence
(
    next_val bigint null
);

create or replace table tab_configs
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    code varchar(255) not null,
    name varchar(255) not null,
    entity_id bigint not null,
    constraint UK_gxop1v4wyo0q1av8sok0ylr9
        unique (code),
    constraint UK_m0ssjjiuwj5i4fv03pojf2i28
        unique (name),
    constraint FK8k7gvsklmd9jomm8frqure17x
        foreign key (entity_id) references entities (id)
);

create or replace table tab_config_entries
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    code varchar(255) not null,
    tab_config_id bigint not null,
    title varchar(255) not null,
    constraint FKeja2q8qpgdm76eb3ntvxfnu0d
        foreign key (tab_config_id) references tab_configs (id)
);

create or replace table tab_config_entry_components
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    code varchar(255) not null,
    link varchar(255) not null,
    type int not null,
    tab_config_entry_id bigint not null,
    constraint FKilbf0mt2fdwhxugfilrekjm7t
        foreign key (tab_config_entry_id) references tab_config_entries (id)
);

create or replace table table_configs
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    code varchar(255) not null,
    display_pagination bit not null,
    entity_id bigint not null,
    constraint UK_h5yns1yartfn5kp90jqp31385
        foreign key (entity_id) references entities (id)
);

create or replace table config_entry_rel
(
    config_id bigint not null,
    entity_entry_id bigint not null,
    primary key (config_id, entity_entry_id),
    constraint FK78rowxy1suo52csr34vokd13r
        foreign key (entity_entry_id) references entity_entries (id),
    constraint FKtqgobn3gl30r0fb4wtn659s1a
        foreign key (config_id) references table_configs (id)
);

create or replace table table_group_actions
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    code varchar(255) not null,
    name varchar(255) not null,
    table_config_id bigint not null,
    constraint FKp6bx8nigwnewh7y8ia2w0dwfo
        foreign key (table_config_id) references table_configs (id)
);

create or replace table table_config_actions
(
    id bigint not null
        primary key,
    created_at datetime not null,
    updated_at datetime not null,
    code varchar(255) not null,
    btn_type varchar(255) not null,
    icon varchar(255) null,
    link varchar(255) not null,
    text varchar(255) not null,
    config_group_id bigint not null,
    constraint FK9ht9t8pq8awg08xuadc0b1tp3
        foreign key (config_group_id) references table_group_actions (id)
);

