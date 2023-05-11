create table words (
                       id                    bigserial,
                       word                  varchar(30) not null ,
                       translate             varchar(80) not null,
                       learning_status       smallint,
                       context               varchar(300),
                       user_id               bigserial,
                       last_study            timestamp,
                       primary key (id)
);

create table Dictionary (
                       id                    bigserial,
                       dictionary_name       varchar(30) not null ,
                       primary key (id)
);

CREATE TABLE dictionary_words (
                             dictionary_id               bigserial not null,
                             word_id                     bigserial not null,
                             primary key (dictionary_id, word_id),
                             foreign key (dictionary_id) references Dictionary (id),
                             foreign key (word_id) references words (id)
);

insert into Dictionary (dictionary_name)
values
    ('New Dictionary#1'), ('New Dictionary#2');

create table users (
  id                    bigserial,
  username              varchar(30) not null ,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1);

