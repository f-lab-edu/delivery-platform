ALTER TABLE delivery
    ADD COLUMN rider_id BIGINT;

ALTER TABLE delivery
    ADD COLUMN shop_id BIGINT;

ALTER TABLE delivery
    ADD COLUMN longitude DOUBLE;

ALTER TABLE delivery
    ADD COLUMN latitude DOUBLE;
