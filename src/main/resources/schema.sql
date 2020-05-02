drop database aurora;
create schema aurora collate latin1_swedish_ci;
use aurora;

create or replace table application_user
(
	id bigint auto_increment
		primary key,
	password varchar(255) not null,
	role varchar(255) default 'ADMIN' not null,
	username varchar(255) default 'username' not null
);

create or replace table entity_actions
(
	id bigint not null
		primary key,
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
	entity_code varchar(255) not null,
	existent bit not null,
	multiple bit not null,
	rules varchar(255) null,
	text varchar(255) not null,
	constraint UK_4gcrbunbqs5oxjafs1wdgs5uj
		unique (entity_code),
	constraint UK_qmhvxxxqrhi8kk56yx35u3xuo
		unique (code)
);

create or replace table farms
(
	id bigint not null
		primary key,
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
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
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
	name varchar(255) not null,
	version bigint not null,
	farm_id bigint not null,
	constraint UK_5qu23ja9ixrm2nfbqa7xl3wab
		unique (code),
	constraint FKmviauev05fby66gs0430l5w6
		foreign key (farm_id) references farms (id)
);

create or replace table entity_entries
(
	id bigint not null
		primary key,
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
	entity_code varchar(255) not null,
	type varchar(255) not null,
	entity_id bigint not null,
	farm_id bigint not null,
	constraint UK_30nn6agh98d22guncxt5qgt43
		unique (code),
	constraint UK_5eqc656rcdiijxu8ibnmviwy5
		unique (entity_code),
	constraint FKbdc35xvfg95dj1gd87p2gdoh7
		foreign key (entity_id) references entities (id),
	constraint FKkih2b5uv6qedy9tad2y3mjq4u
		foreign key (farm_id) references farms (id)
);

create or replace table form_configs
(
	id bigint not null
		primary key,
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
	entity_code varchar(255) not null,
	constraint UK_bvx49hbjnyehwi6705bryj3q9
		unique (code),
	constraint UK_j01gg149ayc0pxc322gcdlc47
		unique (entity_code)
);

create or replace table form_config_model_entries
(
	form_config_model_id bigint not null,
	entries varchar(255) null,
	constraint FK5pvxymkbdbqqldjxw0i905lrm
		foreign key (form_config_model_id) references form_configs (id)
);

create or replace table hibernate_sequence
(
	next_val bigint null
);

create or replace table tab_configs
(
	id bigint not null
		primary key,
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
	entity_code varchar(255) not null,
	name varchar(255) not null,
	constraint UK_fuwjdprq3prh64skldf6t2ekh
		unique (entity_code),
	constraint UK_gxop1v4wyo0q1av8sok0ylr9
		unique (code),
	constraint UK_m0ssjjiuwj5i4fv03pojf2i28
		unique (name)
);

create or replace table tab_config_entries
(
	id bigint not null
		primary key,
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
	entity_entry_code varchar(255) not null,
	title varchar(255) not null,
	tab_config_id bigint not null,
	constraint UK_mfduldv731ibxkgo6g2xg75vs
		unique (code),
	constraint FKk15t8f4okqwtai7f96b7qae6j
		foreign key (tab_config_id) references tab_configs (id)
);

create or replace table tab_config_entry_components
(
	id bigint not null
		primary key,
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
	link varchar(255) not null,
	type int not null,
	tab_config_entry_id bigint not null,
	constraint UK_hhd063asdqwpb9t9g7qtpowat
		unique (code),
	constraint FKnk3xavtdx8kskm8w6vfmgj8kv
		foreign key (tab_config_entry_id) references tab_config_entries (id)
);

create or replace table table_configs
(
	id bigint not null
		primary key,
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
	display_pagination bit not null,
	entity_code varchar(255) not null,
	constraint UK_ihqp14vpbmgabor0mamq9vixg
		unique (code),
	constraint UK_ksmce8sphh2wgnebf3nl25nxb
		unique (entity_code)
);

create or replace table table_config_model_entries
(
	table_config_model_id bigint not null,
	entries varchar(255) null,
	constraint FK6qysvspamvmpgb4u4iywgmmpw
		foreign key (table_config_model_id) references table_configs (id)
);

create or replace table table_group_actions
(
	id bigint not null
		primary key,
	code varchar(255) not null,
	created_at datetime not null,
	updated_at datetime not null,
	name varchar(255) not null,
	table_config_id bigint not null,
	constraint UK_511hy06qtg7clg04smohmkfro
		unique (code),
	constraint FKp6bx8nigwnewh7y8ia2w0dwfo
		foreign key (table_config_id) references table_configs (id)
);

