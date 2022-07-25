create table books
(
    id        bigserial primary key,
    title     varchar(255),
    genre_id  bigint,
    author_id bigint
);
