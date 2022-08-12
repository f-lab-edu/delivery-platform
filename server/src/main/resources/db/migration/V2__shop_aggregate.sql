CREATE TABLE shop
(
    shop_id         BIGINT      NOT NULL AUTO_INCREMENT,
    name            VARCHAR(50) NOT NULL,
    phone_number    VARCHAR(50) NOT NULL,
    zip_code        VARCHAR(50) NOT NULL,
    country         VARCHAR(50) NOT NULL,
    state           VARCHAR(50) NOT NULL,
    city            VARCHAR(50) NOT NULL,
    street          VARCHAR(50) NOT NULL,
    status          VARCHAR(50) NOT NULL,
    min_order_price INT         NOT NULL DEFAULT 0,
    PRIMARY KEY (shop_id)
) CHARACTER SET utf8mb4;

CREATE TABLE menu
(
    menu_id       BIGINT        NOT NULL AUTO_INCREMENT,
    name          VARCHAR(50)   NOT NULL,
    display_order INT           NOT NULL,
    description   VARCHAR(1000) NOT NULL,
    shop_id       BIGINT        NOT NULL,
    PRIMARY KEY (menu_id)
) CHARACTER SET utf8mb4;

CREATE TABLE option_group
(
    option_group_id BIGINT      NOT NULL AUTO_INCREMENT,
    name            VARCHAR(50) NOT NULL,
    display_order   INT         NOT NULL,
    menu_id         BIGINT      NOT NULL,
    PRIMARY KEY (option_group_id)
) CHARACTER SET utf8mb4;


CREATE TABLE options
(
    option_id       BIGINT      NOT NULL AUTO_INCREMENT,
    name            VARCHAR(50) NOT NULL,
    price           INT         NOT NULL,
    display_order   INT         NOT NULL,
    option_group_id BIGINT      NOT NULL,
    PRIMARY KEY (option_id)
) CHARACTER SET utf8mb4;
