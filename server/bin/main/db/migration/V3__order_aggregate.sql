CREATE TABLE orders
(
    order_id    BIGINT      NOT NULL AUTO_INCREMENT,
    shop_id     BIGINT      NOT NULL,
    member_id   BIGINT      NOT NULL,
    total_price INT         NOT NULL,
    status      VARCHAR(50) NOT NULL,
    PRIMARY KEY (order_id),
    INDEX idx__orders_shop_id (shop_id),
    INDEX idx__orders_member_id (member_id)
) CHARACTER SET utf8mb4;

CREATE TABLE order_line_item
(
    order_line_item_id    BIGINT      NOT NULL AUTO_INCREMENT,
    menu_id               BIGINT      NOT NULL,
    name                  VARCHAR(50) NOT NULL,
    count                 INT         NOT NULL,
    total_price           INT         NOT NULL,
    order_id              BIGINT,
    PRIMARY KEY (order_line_item_id),
    INDEX idx__order_line_item_order_id (order_id),
    INDEX idx__order_line_item_menu_id (menu_id)
) CHARACTER SET utf8mb4;

CREATE TABLE order_option_group
(
    order_option_group_id   BIGINT      NOT NULL AUTO_INCREMENT,
    name                    VARCHAR(50) NOT NULL,
    order_line_item_id      BIGINT,
    PRIMARY KEY (order_option_group_id),
    INDEX idx__order_option_group_order_line_item_id (order_line_item_id)
) CHARACTER SET utf8mb4;

CREATE TABLE order_option
(
    order_option_id         BIGINT      NOT NULL AUTO_INCREMENT,
    name                    VARCHAR(50) NOT NULL,
    price                   INT         NOT NULL,
    order_option_group_id   BIGINT,
    PRIMARY KEY (order_option_id),
    INDEX idx__order_option_order_option_group_id (order_option_group_id)
) CHARACTER SET utf8mb4;
