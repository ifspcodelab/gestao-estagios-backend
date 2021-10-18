CREATE TABLE advisor_requests(
    id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    internship_type VARCHAR NOT NULL,
    details VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    student_id UUID NOT NULL,
    curriculum_id UUID NOT NULL,
    advisor_id UUID NOT NULL,
    CONSTRAINT advisor_requests_pk PRIMARY KEY (id),
    CONSTRAINT student_fk FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT curriculum_fk FOREIGN key (curriculum_id) REFERENCES curriculums(id),
    CONSTRAINT advisor_fk FOREIGN key (advisor_id) REFERENCES advisors(id)
);