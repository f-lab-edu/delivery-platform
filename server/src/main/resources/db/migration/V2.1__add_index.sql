ALTER TABLE menu
    ADD INDEX menu_idx01 (shop_id);

ALTER TABLE option_group
    ADD INDEX option_group_idx01 (menu_id);

ALTER TABLE options
    ADD INDEX options_idx01 (option_group_id);
