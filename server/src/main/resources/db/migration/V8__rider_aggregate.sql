CREATE TABLE rider
(
    rider_id        BIGINT      NOT NULL AUTO_INCREMENT,
    user_id         BIGINT      NOT NULL,
    longitude       DOUBLE,
    latitude        DOUBLE,
    status          VARCHAR(50) NOT NULL,
    PRIMARY KEY (rider_id),
    INDEX idx__rider_user_id (user_id)
) CHARACTER SET utf8mb4;
