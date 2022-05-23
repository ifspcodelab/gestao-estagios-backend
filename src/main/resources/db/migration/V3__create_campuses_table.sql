CREATE TABLE campuses(
    id UUID NOT NULL,
    name VARCHAR NOT NULL,
    abbreviation VARCHAR NOT NULL UNIQUE,
    initial_registration_pattern VARCHAR NOT NULL UNIQUE,
    postal_code VARCHAR NOT NULL,
    street VARCHAR NOT NULL,
    neighborhood VARCHAR NOT NULL,
    city_id UUID NOT NULL,
    number VARCHAR NOT NULL,
    complement VARCHAR,
    telephone VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    website VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    CONSTRAINT campuses_pk PRIMARY KEY (id),
    CONSTRAINT cities_fk FOREIGN KEY (city_id) REFERENCES cities(id)
);