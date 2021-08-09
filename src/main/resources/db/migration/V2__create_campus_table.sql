CREATE TABLE campus(
    id UUID NOT NULL,
    name VARCHAR NOT NULL,
    abbreviation  VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    telephone VARCHAR NOT NULL,
    site_sector VARCHAR NOT NULL,
    CONSTRAINT campus_pk PRIMARY KEY (id)
);