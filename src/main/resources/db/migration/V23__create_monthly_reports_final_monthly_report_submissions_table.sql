CREATE TABLE monthly_reports_final_monthly_report_submissions(
     monthly_report_id UUID NOT NULL,
     final_monthly_report_submissions_id UUID NOT NULL,
     CONSTRAINT monthly_report_fk FOREIGN KEY (monthly_report_id) REFERENCES monthly_reports(id),
     CONSTRAINT final_monthly_report_submissions_fk FOREIGN KEY (final_monthly_report_submissions_id) REFERENCES final_monthly_report_submissions(id)
)