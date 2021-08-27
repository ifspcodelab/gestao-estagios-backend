CREATE TABLE curriculums(
    id UUID NOT NULL,
    code VARCHAR NOT NULL,
    course_load INT NOT NULL,
    internship_course_load INT NOT NULL,
    internship_start_criteria VARCHAR NOT NULL,
    internship_allowed_activities VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    course_id UUID NOT NULL,
    CONSTRAINT curriculums_pk PRIMARY KEY (id),
    CONSTRAINT courses_fk FOREIGN KEY (course_id) REFERENCES courses(id)
);