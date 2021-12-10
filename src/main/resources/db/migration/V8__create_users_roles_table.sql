CREATE TABLE users_roles(
    user_id UUID NOT NULL,
    role VARCHAR NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users_roles VALUES ('9dfc3dbf-b2ca-4111-95f4-074d9cd847d1', 'ROLE_ADMIN');