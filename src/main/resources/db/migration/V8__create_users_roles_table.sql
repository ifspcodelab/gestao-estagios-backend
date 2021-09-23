CREATE TABLE users_roles(
    user_id UUID NOT NULL,
    role VARCHAR NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users(id)
);