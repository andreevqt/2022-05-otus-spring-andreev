create table comments
(
    id      bigserial,
    book_id bigint,
    content text,
    foreign key (book_id) REFERENCES books (id) on delete cascade
)
