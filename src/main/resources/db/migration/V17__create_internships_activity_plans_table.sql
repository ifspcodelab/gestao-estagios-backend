CREATE TABLE internships_activity_plans(
    internship_id UUID NOT NULL,
    activity_plans_id UUID NOT NULL,
    CONSTRAINT internships_fk FOREiGN KEY (internship_id) REFERENCES internships(id),
    CONSTRAINT advisor_request_fk FOREIGN key (activity_plans_id) REFERENCES activity_plans(id)
);