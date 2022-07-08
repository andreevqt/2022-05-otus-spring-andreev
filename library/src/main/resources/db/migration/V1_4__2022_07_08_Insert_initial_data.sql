-- genres
insert into genres (title) 
values ('Adventures'),
       ('Novels');

-- authors
insert into authors (name) 
values ('Lewis Carroll'),
       ('J. K. Rowling'),
       ('Jane Austen');

-- books
insert into books (title, genre_id, author_id) 
values ('Alice''s Adventures in Wonderland', 1, 1),
       ('Harry Potter and the Philosopher''s Stone', 1, 2),
       ('Pride and Prejudice', 2, 3);
