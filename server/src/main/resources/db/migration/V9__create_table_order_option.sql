CREATE TABLE order_option
(
    order_option_id         BIGINT      NOT NULL AUTO_INCREMENT,
    name                    VARCHAR(50) NOT NULL,
    price                   INT         NOT NULL,
    order_option_group_id   BIGINT,
    PRIMARY KEY (order_option_id)
) CHARACTER SET utf8mb4;
