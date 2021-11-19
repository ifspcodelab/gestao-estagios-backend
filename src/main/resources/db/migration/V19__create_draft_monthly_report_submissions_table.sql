CREATE TABLE draft_monthly_report_submissions(
    id UUID NOT NULL,
    submission_date DATE NOT NULL,
    report_start_date DATE NOT NULL,
    report_end_date DATE NOT NULL,
    draft_monthly_report_url VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    details VARCHAR,
    number_of_approved_hours INT,
    CONSTRAINT draft_monthly_report_submissions_pk PRIMARY KEY (id)
);