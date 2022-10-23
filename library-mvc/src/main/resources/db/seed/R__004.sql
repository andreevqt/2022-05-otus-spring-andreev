-- users
insert into users (username, password, enabled)
values ('user', '$2a$10$vDtp6JF0SLjvsrmcctmPvOSyfh6skMQSsws6V1ft6jPThaIE8wwJu', 1);

-- authorities
insert into authorities (username, authority)
values ('user', 'ADMIN');

