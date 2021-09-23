CREATE TABLE users(
    id UUID NOT NULL,
    registration VARCHAR NOT NULL UNIQUE,
    name VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    CONSTRAINT users_pk PRIMARY KEY (id)
);