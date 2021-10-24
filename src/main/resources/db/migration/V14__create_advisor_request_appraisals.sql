CREATE TABLE advisor_request_appraisals(
                                 id UUID NOT NULL,
                                 created_at TIMESTAMP NOT NULL,
                                 details VARCHAR NOT NULL,
                                 is_deferred BOOLEAN NOT NULL,
                                 meeting_date TIMESTAMP NOT NULL,
                                 expires_at TIMESTAMP NOT NULL,
                                 internship_type VARCHAR NOT NULL,
                                 advisor_request_id UUID NOT NULL,
                                 CONSTRAINT advisor_request_appraisals_Pk PRIMARY KEY (id),
                                 CONSTRAINT advisor_request_fk FOREIGN KEY (advisor_request_id) REFERENCES advisor_requests(id)
);