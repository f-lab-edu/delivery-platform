CREATE TABLE menu
(
    menu_id       BIGINT      NOT NULL AUTO_INCREMENT,
    name          VARCHAR(50) NOT NULL,
    price         INT         NOT NULL,
    shop_id       BIGINT      NOT NULL,
    PRIMARY KEY (menu_id)
) CHARACTER SET utf8mb4;
