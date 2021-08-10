CREATE TABLE campuses(
    id UUID NOT NULL,
    name VARCHAR NOT NULL,
    abbreviation VARCHAR NOT NULL UNIQUE,
    postal_code VARCHAR NOT NULL,
    street VARCHAR NOT NULL,
    neighborhood VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    state VARCHAR NOT NULL,
    number VARCHAR NOT NULL,
    complement VARCHAR NOT NULL,
    telephone VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    website VARCHAR NOT NULL,
    CONSTRAINT campuses_pk PRIMARY KEY (id)
);