CREATE TABLE orders
(
    order_id    BIGINT      NOT NULL AUTO_INCREMENT,
    shop_id     BIGINT      NOT NULL,
    member_id   BIGINT      NOT NULL,
    status      VARCHAR(50) NOT NULL,
    PRIMARY KEY (order_id)
) CHARACTER SET utf8mb4;
