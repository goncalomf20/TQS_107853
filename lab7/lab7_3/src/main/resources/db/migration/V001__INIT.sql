CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    pages INT
);

INSERT INTO book (title, author, pages) VALUES
('Book 1', 'Author 1', 200),
('Book 2', 'Author 2', 250);
