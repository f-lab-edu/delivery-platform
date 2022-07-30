CREATE TABLE options
(
    option_id         BIGINT      NOT NULL AUTO_INCREMENT,
    name              VARCHAR(50) NOT NULL,
    additional_price  INT         NOT NULL,
    option_group_id   BIGINT      NOT NULL,
    PRIMARY KEY (option_id)
) CHARACTER SET utf8mb4;
