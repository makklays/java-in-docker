
-- V10__update_bases_table.sql
-- Migration #10: update table bases

-- Updating table 'bases' for adding field 'img'
ALTER TABLE bases ADD COLUMN img VARCHAR(255) DEFAULT NULL;

