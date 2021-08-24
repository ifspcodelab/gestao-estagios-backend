CREATE TABLE cities (
    id UUID NOT NULL,
    name VARCHAR NOT NULL,
    state_abbreviation VARCHAR NOT NULL,
    CONSTRAINT cities_pk PRIMARY KEY (id),
    CONSTRAINT states_fk FOREIGN KEY (state_abbreviation) REFERENCES states(abbreviation)
);

