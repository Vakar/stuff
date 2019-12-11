CREATE TABLE IF NOT EXISTS APP_USER
(
    ID       SERIAL,
    USERNAME VARCHAR(256) NOT NULL,
    EMAIL    VARCHAR(256) NOT NULL,
    PSWD     VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS STUFF
(
    ID          SERIAL,
    NAME        VARCHAR(255) NOT NULL,
    BRAND       VARCHAR(255) NOT NULL,
    DESCRIPTION TEXT         NOT NULL,
    COST        FLOAT        NOT NULL,
    PICTURE     BLOB         NOT NULL,
    USER_ID     INT REFERENCES APP_USER (ID)
);

