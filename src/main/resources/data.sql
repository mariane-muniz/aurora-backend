INSERT INTO aurora.application_user (id, password, role, username) VALUES (1, '$2a$10$JNQbg4aiFs34AF/94DIAXunbbJKMdSgRfpUKkX/FM4wd320/drWle', 'ADMIN', 'username');
INSERT INTO aurora.farms (id, created_at, updated_at, code, name) VALUES (2, '2020-03-15 10:14:59', '2020-03-15 10:15:01', 'farm-catalog', 'Catalog');
INSERT INTO aurora.entities (id, created_at, updated_at, code, name, farm_id) VALUES (3, '2020-03-15 13:27:23', '2020-03-15 13:27:23', 'product', 'Product', 2);
INSERT INTO aurora.table_configs (id, created_at, updated_at, display_pagination, entity_id) VALUES (4, '2020-03-15 13:22:17', '2020-03-15 13:22:17', false, 3);
INSERT INTO aurora.entity_entries (id, created_at, updated_at, description, name, public_name, type, entity_id, farm_id) VALUES (5, '2020-03-15 14:18:40', '2020-03-15 14:18:40', 'Status do produto', 'status', 'Status', 3, 3, 2);
INSERT INTO aurora.hibernate_sequence (next_val) VALUES (6);