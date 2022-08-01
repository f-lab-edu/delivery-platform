CREATE TABLE order_option_group
(
    order_option_group_id   BIGINT      NOT NULL AUTO_INCREMENT,
    name                    VARCHAR(50) NOT NULL,
    order_line_item_id      BIGINT,
    PRIMARY KEY (order_option_group_id)
) CHARACTER SET utf8mb4;
