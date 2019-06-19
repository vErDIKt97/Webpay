create sequence hibernate_sequence start 1 increment 1;
create table message (
                         id int8 not null,
                         filename varchar(255),
                         tag varchar(255),
                         text varchar(2048) not null,
                         user_id int8,
                         primary key (id)
);
create table pfl (
                     id int8 not null,
                     file_name varchar(255),
                     list bytea,
                     time_of_load timestamp,
                     primary key (id)
);
create table user_role (
                           user_id int8 not null,
                           roles varchar(255)
);
create table usr (
                     id int8 not null,
                     surname varchar(255) not null,
                     active boolean not null,
                     name varchar(255) not null,
                     password varchar(255) not null,
                     sells varchar(255),
                     username varchar(255) not null,
                     primary key (id)
);
alter table if exists message
    add constraint message_user_fk
        foreign key (user_id) references usr;
alter table if exists user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr;