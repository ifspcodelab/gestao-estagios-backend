CREATE TABLE realization_terms(
    id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    term_start_date DATE,
    term_end_date DATE,
    realization_term_url VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    details VARCHAR,
    internship_id UUID NOT NULL,
    CONSTRAINT realization_terms_pk PRIMARY KEY (id),
    CONSTRAINT internship_fk FOREIGN KEY (internship_id) REFERENCES internships(id)
)