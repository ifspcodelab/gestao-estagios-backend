CREATE TABLE departments(
    id UUID NOT NULL,
    abbreviation VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    campus_id UUID NOT NULL,
    CONSTRAINT departments_pk PRIMARY KEY (id),
    CONSTRAINT campus_fk FOREIGN KEY (campus_id) REFERENCES campus(id)
);