CREATE TABLE departments(
     id UUID NOT NULL,
     name VARCHAR NOT NULL,
     abbreviation VARCHAR NOT NULL,
     status VARCHAR NOT NULL,
     campus_id UUID NOT NULL,
     CONSTRAINT departments_pk PRIMARY KEY (id),
     CONSTRAINT campuses_fk FOREIGN KEY (campus_id) REFERENCES campuses(id)
);