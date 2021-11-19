CREATE TABLE monthly_reports_draft_monthly_report_submissions(
    monthly_report_id UUID NOT NULL,
    draft_monthly_report_submissions_id UUID NOT NULL,
    CONSTRAINT monthly_report_fk FOREIGN KEY (monthly_report_id) REFERENCES monthly_reports(id),
    CONSTRAINT draft_monthly_report_submissions_fk FOREIGN KEY (draft_monthly_report_submissions_id) REFERENCES draft_monthly_report_submissions(id)
);