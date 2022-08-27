create table books
(
    id        bigserial primary key,
    title     varchar(255),
    genre_id  bigint,
    author_id bigint,
    foreign key (author_id) REFERENCES authors (id) on delete cascade
);
