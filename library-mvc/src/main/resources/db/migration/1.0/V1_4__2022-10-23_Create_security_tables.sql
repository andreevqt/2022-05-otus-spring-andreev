create table users
(
    id bigserial primary key,
    username varchar(255) not null unique,
    password varchar(255) not null,
    enabled  boolean      not null default false
);

create table authorities
(
    user_id  bigint,
    authority varchar(255) not null,
    foreign key (user_id) references users (id)
);

create unique index ix_username
    on users (username);

create unique index ix_auth_username
    on authorities (user_id, authority);
