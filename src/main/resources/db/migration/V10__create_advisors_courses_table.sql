create table advisors_courses(
    advisor_id UUID NOT NULL,
    courses_id UUID NOT NULL,
    CONSTRAINT advisors_fk FOREIGN KEY (advisor_id) REFERENCES advisors(id),
    CONSTRAINT courses_fk FOREIGN KEY (courses_id) REFERENCES courses(id)
)