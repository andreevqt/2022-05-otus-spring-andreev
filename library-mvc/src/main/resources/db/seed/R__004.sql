-- users
-- password = password
insert into users (username, password, enabled)
values ('admin', '$2a$10$vDtp6JF0SLjvsrmcctmPvOSyfh6skMQSsws6V1ft6jPThaIE8wwJu', 1),
       ('user', '$2a$10$vDtp6JF0SLjvsrmcctmPvOSyfh6skMQSsws6V1ft6jPThaIE8wwJu', 1);

-- authorities
insert into authorities (user_id, authority)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_GUEST');

