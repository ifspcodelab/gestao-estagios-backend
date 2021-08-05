CREATE TABLE courses(
    id UUID NOT NULL,
    name VARCHAR NOT NULL,
    abbreviation VARCHAR NOT NULL,
    number_of_periods INT NOT NULL,
    department_id UUID NOT NULL,
    CONSTRAINT courses_pk PRIMARY KEY (id),
    CONSTRAINT departments_fk FOREIGN KEY (department_id) REFERENCES departments(id)
);