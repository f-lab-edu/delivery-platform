CREATE TABLE order_line_item
(
    order_line_item_id    BIGINT      NOT NULL AUTO_INCREMENT,
    menu_id               BIGINT      NOT NULL,
    name                  VARCHAR(50) NOT NULL,
    count                 INT         NOT NULL,
    order_id              BIGINT,
    PRIMARY KEY (order_line_item_id)
) CHARACTER SET utf8mb4;
