create table users
(
    username varchar(255) not null,
    password varchar(255) not null,
    enabled  boolean      not null default false,
    primary key (username)
);

create table authorities
(
    username  varchar(255) not null,
    authority varchar(255) not null,
    foreign key (username) references users (username)
);

create unique index ix_auth_username
    on authorities (username, authority);
