ALTER TABLE menu
    ADD INDEX idx__menu_shop_id (shop_id);

ALTER TABLE option_group
    ADD INDEX idx__option_group_menu_id (menu_id);

ALTER TABLE options
    ADD INDEX idx__options_option_group_id (option_group_id);
