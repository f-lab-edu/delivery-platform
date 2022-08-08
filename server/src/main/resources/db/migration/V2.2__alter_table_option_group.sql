ALTER TABLE option_group
    ADD COLUMN basic tinyint(1) NOT NULL;

ALTER TABLE option_group
    ADD COLUMN exclusive tinyint(1) NOT NULL;
