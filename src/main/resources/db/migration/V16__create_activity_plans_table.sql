CREATE TABLE activity_plans(
    id UUID NOT NULL,
    company_name VARCHAR,
    internship_start_date TIMESTAMP,
    internship_end_date TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    activity_plan_url VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    details VARCHAR,
    internship_id UUID NOT NULL,
    CONSTRAINT activity_plans_pk PRIMARY KEY (id),
    CONSTRAINT internship_fk FOREIGN KEY (internship_id) REFERENCES internships(id)
)