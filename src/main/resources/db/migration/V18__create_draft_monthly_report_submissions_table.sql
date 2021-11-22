CREATE TABLE draft_monthly_report_submissions(
    id UUID NOT NULL,
    submission_date DATE NOT NULL,
    report_start_date DATE,
    report_end_date DATE,
    draft_monthly_report_url VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    details VARCHAR,
    number_of_approved_hours INT,
    monthly_report_id UUID NOT NULL,
    CONSTRAINT draft_monthly_report_submissions_pk PRIMARY KEY (id),
    CONSTRAINT monthly_reports_fk FOREIGN KEY (monthly_report_id) REFERENCES monthly_reports(id)
);