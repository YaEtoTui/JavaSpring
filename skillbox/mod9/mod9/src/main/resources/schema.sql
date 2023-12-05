DROP TABLE IF EXISTS book;

CREATE TABLE book(
id INT PRIMARY KEY,
pub_date DATE NOT NULL,
is_bestseller INT NOT NULL,
slug VARCHAR(250) NOT NULL,
title VARCHAR(250) NOT NULL,
image VARCHAR(250) NOT NULL,
description VARCHAR(500) NOT NULL,
price INT NOT NULL,
discount DOUBLE PRECISION NOT NULL,
author_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS author;

create table author (
                         id INT,
                         first_name VARCHAR(50),
                         last_name VARCHAR(50)
);