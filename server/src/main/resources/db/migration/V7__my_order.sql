CREATE TABLE my_order
(
    member_id             BIGINT        NOT NULL,
    order_id              BIGINT        NOT NULL,
    order_status          VARCHAR(50)   NOT NULL,
    order_total_price     INT           NOT NULL,
    order_line_items      JSON          NOT NULL,
    shop_name             VARCHAR(50)   NOT NULL,
    delivery_status       VARCHAR(50)   NOT NULL,
    PRIMARY KEY (member_id)
) CHARACTER SET utf8mb4;
