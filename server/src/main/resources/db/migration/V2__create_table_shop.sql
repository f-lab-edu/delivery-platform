CREATE TABLE shop
(
    shop_id       BIGINT      NOT NULL AUTO_INCREMENT,
    name          VARCHAR(50) NOT NULL,
    phone_number  VARCHAR(50) NOT NULL,
    zip_code      VARCHAR(50) NOT NULL,
    country       VARCHAR(50) NOT NULL,
    state         VARCHAR(50) NOT NULL,
    city          VARCHAR(50) NOT NULL,
    street        VARCHAR(50) NOT NULL,
    status        VARCHAR(50) NOT NULL,
    PRIMARY KEY (shop_id)
) CHARACTER SET utf8mb4;
