INSERT INTO aurora.application_user (id, password, role, username) VALUES (1, '$2a$10$JNQbg4aiFs34AF/94DIAXunbbJKMdSgRfpUKkX/FM4wd320/drWle', 'ADMIN', 'username');

INSERT INTO aurora.farms (id, created_at, updated_at, code, name) VALUES (2, '2020-03-15 10:14:59', '2020-03-15 10:15:01', 'farm-catalog', 'Catalog');

INSERT INTO aurora.entities (id, created_at, updated_at, code, name, farm_id) VALUES (3, '2020-03-15 13:27:23', '2020-03-15 13:27:23', 'product', 'Product', 2);
INSERT INTO aurora.entity_entries (id, created_at, updated_at, description, name, public_name, type, entity_id, farm_id) VALUES (5, '2020-03-15 14:18:40', '2020-03-15 14:18:40', 'Status do produto', 'status', 'Status', 3, 3, 2);
INSERT INTO aurora.entity_entries (id, created_at, updated_at, description, name, public_name, type, entity_id, farm_id) VALUES (6, '2020-03-23 21:30:55', '2020-03-23 21:30:57', 'Nome do produto', 'name', 'Nome', 0, 3, 2);
INSERT INTO aurora.entity_entries (id, created_at, updated_at, description, name, public_name, type, entity_id, farm_id) VALUES (7, '2020-03-23 21:32:39', '2020-03-23 21:32:42', 'Codigo do produto', 'code', 'Codigo', 0, 3, 2);

INSERT INTO aurora.tab_configs (id, created_at, updated_at, code, name, entity_id) VALUES (8, '2020-03-26 18:57:56', '2020-03-26 18:57:58', 'tab', 'tab', 3);
INSERT INTO aurora.tab_config_entries (id, created_at, updated_at, tab_config_id, title) VALUES (9, '2020-03-26 19:10:56', '2020-03-26 19:10:57', 8, 'General');
INSERT INTO aurora.tab_config_entries (id, created_at, updated_at, tab_config_id, title) VALUES (14, '2020-03-26 19:10:56', '2020-03-26 19:10:57', 8, 'Attributes');
INSERT INTO aurora.tab_config_entry_components (id, created_at, updated_at, link, type, tab_config_entry_id) VALUES (10, '2020-03-26 19:13:18', '2020-03-26 19:13:19', 'product', 2, 9);
INSERT INTO aurora.tab_config_entry_components (id, created_at, updated_at, link, type, tab_config_entry_id) VALUES (15, '2020-03-26 19:13:18', '2020-03-26 19:13:19', 'product', 1, 14);

INSERT INTO aurora.table_configs (id, created_at, updated_at, display_pagination, entity_id) VALUES (4, '2020-03-15 13:22:17', '2020-03-15 13:22:17', true, 3);
INSERT INTO aurora.table_group_actions (id, created_at, updated_at, name, table_config_id) VALUES (11, '2020-03-27 19:37:28', '2020-03-27 19:37:28', 'General', 4);
INSERT INTO aurora.table_config_actions (id, created_at, updated_at, btn_type, icon, link, text, config_group_id) VALUES (12, '2020-03-27 19:37:46', '2020-03-27 19:37:48', 'btn btn-sm btn-link', 'plus', '/tab/product', 'Add new', 11);

INSERT INTO aurora.form_configs (id, created_at, updated_at, entity_id) VALUES (13, '2020-03-27 20:26:05', '2020-03-27 20:26:06', 3);
INSERT INTO aurora.form_entry_rel (config_id, entity_entry_id) VALUES (13, 5);
INSERT INTO aurora.form_entry_rel (config_id, entity_entry_id) VALUES (13, 6);
INSERT INTO aurora.form_entry_rel (config_id, entity_entry_id) VALUES (13, 7);

INSERT INTO aurora.hibernate_sequence (next_val) VALUES (16);