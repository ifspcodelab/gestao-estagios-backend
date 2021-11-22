CREATE TABLE final_monthly_report_submissions(
    id UUID NOT NULL,
    submission_date DATE NOT NULL,
    final_monthly_report_url VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    details VARCHAR,
    monthly_report_id UUID NOT NULL,
    CONSTRAINT final_monthly_report_submissions_pk PRIMARY KEY (id),
    CONSTRAINT monthly_reports_fk FOREIGN KEY (monthly_report_id) REFERENCES monthly_reports(id)
)