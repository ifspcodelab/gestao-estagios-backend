CREATE TABLE campus(
    id UUID NOT NULL,
    name VARCHAR NOT NULL UNIQUE,
    CONSTRAINT campus_pk PRIMARY KEY (id)
);