CREATE TABLE category(
    id        INT     NOT NULL PRIMARY KEY,
    name      VARCHAR NOT NULL
);

CREATE TABLE auction(
    id          INT     NOT NULL PRIMARY KEY,
    category_id INT     NOT NULL,
    creator_id  INT     NOT NULL,
    version     INT     NOT NULL,
    title       VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    price       INT     NOT NULL,
    CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT creator_id_fk FOREIGN KEY (creator_id) REFERENCES users (id)
);

CREATE TABLE photo(
    id         INT     NOT NULL PRIMARY KEY,
    auction_id INT     NOT NULL,
    link       VARCHAR NOT NULL,
    CONSTRAINT auction_id_fk FOREIGN KEY (auction_id) REFERENCES auction (id)
);

CREATE TABLE parameter(
    id  INT     NOT NULL PRIMARY KEY,
    key VARCHAR NOT NULL
);

CREATE TABLE auction_parameter(
    auction_id   INT     NOT NULL,
    parameter_id INT     NOT NULL,
    value        VARCHAR NOT NULL,
    CONSTRAINT auction_id_fk FOREIGN KEY (auction_id) REFERENCES auction (id),
    CONSTRAINT parameter_id_fk FOREIGN KEY (parameter_id) REFERENCES parameter (id),
    CONSTRAINT auction_parameter_id PRIMARY KEY (auction_id, parameter_id)
);