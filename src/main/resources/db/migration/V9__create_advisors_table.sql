CREATE TABLE advisors(
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT advisors_pk PRIMARY KEY (id),
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users(id)
);