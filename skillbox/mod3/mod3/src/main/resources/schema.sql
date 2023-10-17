DROP TABLE IF EXISTS authors;

create table authors (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         first_name VARCHAR(50),
                         last_name VARCHAR(50)
);

DROP TABLE IF EXISTS books;

CREATE TABLE  books(
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       author_id BIGINT NOT NULL,
                       foreign key (author_id) references authors (id),
                       title VARCHAR(250) NOT NULL,
                       price_old INTEGER DEFAULT NULL,
                       price INTEGER DEFAULT NULL
);