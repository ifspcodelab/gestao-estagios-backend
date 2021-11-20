CREATE TABLE final_monthly_report_submissions(
    id UUID NOT NULL,
    submission_date DATE NOT NULL,
    final_monthly_report_url VARCHAR NOT NULL,
    details VARCHAR,
    CONSTRAINT final_monthly_report_submissions_pk PRIMARY KEY (id)
)