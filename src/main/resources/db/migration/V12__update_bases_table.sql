
-- V12__update_bases_table.sql
-- Migration #12: update table bases

-- Updating table 'bases' for adding field 'type'
ALTER TABLE bases ADD COLUMN type VARCHAR(255) DEFAULT NULL;