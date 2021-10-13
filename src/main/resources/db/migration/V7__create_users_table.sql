CREATE TABLE users(
    id UUID NOT NULL,
    registration VARCHAR NOT NULL UNIQUE,
    name VARCHAR NOT NULL,
    password VARCHAR,
    email VARCHAR NOT NULL UNIQUE,
    is_activated VARCHAR NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
);