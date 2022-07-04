CREATE TABLE member
(
    member_id    BIGINT NOT NUll,
    nickname     VARCHAR(50),
    email        VARCHAR(50),
    password     VARCHAR(255),
    phone_number VARCHAR(50),
    PRIMARY KEY (member_id)
) CHARACTER SET utf8mb4;

CREATE TABLE owner
(
    owner_id     BIGINT NOT NUll,
    nickname     VARCHAR(50),
    email        VARCHAR(50),
    password     VARCHAR(255),
    phone_number VARCHAR(50),
    PRIMARY KEY (owner_id)
) CHARACTER SET utf8mb4;
