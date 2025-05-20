
-- V11__update_maps_table.sql
-- Migration #11: update table bases

-- Updating table 'maps' for adding field 'build_started_at'
ALTER TABLE maps ADD COLUMN build_started_at DATETIME(6) NULL;

