-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: db/changelog/changelog-master.xml
-- Ran at: 11/1/21, 8:46 PM
-- Against: SA@jdbc:h2:~/Hexlet/java/exercises/migrations/h2db/hexlet
-- Liquibase version: 4.5.0
-- *********************************************************************

-- Lock Database
UPDATE PUBLIC.DATABASECHANGELOGLOCK SET LOCKED = TRUE, LOCKEDBY = '172.25.120.42 (172.25.120.42)', LOCKGRANTED = '2021-11-01 20:46:34.85' WHERE ID = 1 AND LOCKED = FALSE;

-- Release Database Lock
UPDATE PUBLIC.DATABASECHANGELOGLOCK SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

-- Lock Database
UPDATE PUBLIC.DATABASECHANGELOGLOCK SET LOCKED = TRUE, LOCKEDBY = '172.25.120.42 (172.25.120.42)', LOCKGRANTED = '2021-11-01 20:46:36.254' WHERE ID = 1 AND LOCKED = FALSE;

-- Release Database Lock
UPDATE PUBLIC.DATABASECHANGELOGLOCK SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

