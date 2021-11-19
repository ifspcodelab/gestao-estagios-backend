CREATE TABLE monthly_reports(
    id UUID NOT NULL,
    month DATE NOT NULL,
    draft_submitted_on_deadline BOOLEAN,
    final_acceptation_date DATE,
    start_date DATE,
    end_date DATE,
    attachment_url VARCHAR,
    status VARCHAR NOT NULL,
    activity_plan_id UUID NOT NULL,
    CONSTRAINT monthly_reports_pk PRIMARY KEY (id),
    CONSTRAINT activity_plan_fk FOREIGN key (activity_plan_id) REFERENCES activity_plans(id)
);