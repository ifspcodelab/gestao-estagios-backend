CREATE TABLE internships_monthly_reports(
    internship_id UUID NOT NULL,
    monthly_reports_id UUID NOT NULL,
    CONSTRAINT internships_fk FOREIGN KEY (internship_id) REFERENCES internships(id),
    CONSTRAINT monthly_reports_fk FOREIGN KEY (monthly_reports_id) REFERENCES monthly_reports(id)
);