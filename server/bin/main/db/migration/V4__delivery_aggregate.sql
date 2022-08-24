CREATE TABLE delivery
(
    delivery_id    BIGINT      NOT NULL AUTO_INCREMENT,
    order_id       BIGINT      NOT NULL,
    status         VARCHAR(50) NOT NULL,
    PRIMARY KEY (delivery_id),
    INDEX idx__delivery_order_id (order_id)
) CHARACTER SET utf8mb4;
