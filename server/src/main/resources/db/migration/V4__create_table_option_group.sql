CREATE TABLE option_group
(
    option_group_id BIGINT      NOT NULL AUTO_INCREMENT,
    name            VARCHAR(50) NOT NULL,
    menu_id         BIGINT      NOT NULL,
    PRIMARY KEY (option_group_id)
) CHARACTER SET utf8mb4;
