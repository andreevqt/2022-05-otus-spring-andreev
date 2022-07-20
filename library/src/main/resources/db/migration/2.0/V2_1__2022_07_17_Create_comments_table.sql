create table comments
(
    id      bigserial primary key,
    book_id bigint,
    content text,
    foreign key (book_id) REFERENCES books (id) on delete cascade
)
