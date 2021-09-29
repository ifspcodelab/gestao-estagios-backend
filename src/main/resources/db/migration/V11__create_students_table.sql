CREATE TABLE students(
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    curriculum_id UUID NOT NULL,
    CONSTRAINT students_pk PRIMARY KEY (id),
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT curriculum_fk FOREIGN key (curriculum_id) REFERENCES curriculums(id)
);