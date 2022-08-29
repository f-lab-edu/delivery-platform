CREATE TABLE outbox
(
    outbox_id        BIGINT         NOT NULL AUTO_INCREMENT,
    aggregate_type   VARCHAR(100)   NOT NULL,
    aggregate_id     VARCHAR(100)   NOT NULL,
    event_type       VARCHAR(100)   NOT NULL,
    payload          text           NOT NULL,
    PRIMARY KEY (outbox_id)
) CHARACTER SET utf8mb4;
