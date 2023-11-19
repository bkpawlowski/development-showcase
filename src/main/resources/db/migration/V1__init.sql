CREATE SCHEMA devshowcase;

create table devshowcase.t_number
(
    ID    bigserial primary key,
    VALUE bigint not null
);

create table devshowcase.t_generated_number
(
    ID    bigserial primary key,
    VALUE bigint not null
);

create table devshowcase.t_generated_number_generation_time
(
    ID                  bigserial primary key,
    EPOCH_TIME          bigint not null,
    TYPE                text   not null,
    GENERATED_NUMBER_ID bigint,

    foreign key (GENERATED_NUMBER_ID) references devshowcase.t_generated_number (id)
);

insert into devshowcase.t_number(value) values(1);
insert into devshowcase.t_number(value) values(2);
insert into devshowcase.t_number(value) values(3);
insert into devshowcase.t_number(value) values(4);
insert into devshowcase.t_number(value) values(5);
insert into devshowcase.t_number(value) values(6);
insert into devshowcase.t_number(value) values(7);
insert into devshowcase.t_number(value) values(8);
insert into devshowcase.t_number(value) values(9);
insert into devshowcase.t_number(value) values(10);
