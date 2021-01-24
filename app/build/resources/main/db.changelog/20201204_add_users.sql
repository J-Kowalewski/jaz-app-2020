DROP TABLE test1;

CREATE TABLE users(
    id BIGINT NOT NULL,
    username VARCHAR NOT NULL,
    password VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE user_role(
    user_entity_id BIGINT NOT NULL,
    role VARCHAR(100) NOT NULL,

    CONSTRAINT fk_user_id FOREIGN KEY (user_entity_id) REFERENCES users(id)
);