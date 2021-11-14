CREATE TABLE internships(
     id UUID NOT NULL,
     internship_type VARCHAR NOT NULL,
     status VARCHAR NOT NULL,
     advisor_request_id UUID NOT NULL,
     CONSTRAINT internships_pk PRIMARY KEY (id),
     CONSTRAINT advisor_request_fk FOREIGN key (advisor_request_id) REFERENCES advisor_requests(id)
);