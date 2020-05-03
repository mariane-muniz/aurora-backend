INSERT INTO aurora.application_user (id, password, role, username) VALUES (1, '$2a$10$JNQbg4aiFs34AF/94DIAXunbbJKMdSgRfpUKkX/FM4wd320/drWle', 'ADMIN', 'username');

INSERT INTO aurora.farms (id, created_at, updated_at, code, name) VALUES (2, '2020-03-15 10:14:59', '2020-03-15 10:15:01', 'farm-catalog', 'Catalog');

INSERT INTO aurora.table_configs (id, created_at, updated_at, display_pagination, entity_code, code) VALUES (3, '2020-03-15 13:22:17', '2020-03-15 13:22:17', true, 'product', 'product_default');
INSERT INTO aurora.table_configs (id, created_at, updated_at, display_pagination, entity_code, code) VALUES (4, '2020-03-15 13:22:17', '2020-03-15 13:22:17', true, 'general', 'general');
INSERT INTO aurora.table_config_model_entries(table_config_model_id, entries) VALUE (4, 'id');
INSERT INTO aurora.table_config_model_entries(table_config_model_id, entries) VALUE (4, 'code');

INSERT INTO aurora.entity_actions (id, code, created_at, updated_at, entity_code, text, multiple, existent, rules) VALUES (5, 'create_/form/[entity]/[code]', '2020-04-18 21:52:03', '2020-04-18 21:52:03', 'general', 'Salvar', false, false, '');
INSERT INTO aurora.entity_actions (id, code, created_at, updated_at, entity_code, text, multiple, existent, rules) VALUES (6, 'form-entity-delete', '2020-04-18 21:52:03', '2020-04-18 21:52:03','general', 'Remover', false, true, '');
INSERT INTO aurora.entity_actions (id, code, created_at, updated_at, entity_code, text, multiple, existent, rules) VALUES (7, 'update_/form/[entity]/[code]', '2020-04-18 21:52:03', '2020-04-18 21:52:03','general', 'Atualizar', false, true, '');
INSERT INTO aurora.entity_actions (id, code, created_at, updated_at, entity_code, text, multiple, existent, rules) VALUES (8, 'table-entity-delete', '2020-04-18 21:53:10', '2020-04-18 21:53:10','general', 'Remover', true, true, 'MT_[size]_0, LT_[size]_2');
INSERT INTO aurora.entity_actions (id, code, created_at, updated_at, entity_code, text, multiple, existent, rules) VALUES (9, 'navigate_tab/[entity]/[code]', '2020-04-18 21:53:10', '2020-04-18 21:53:10','general', 'Editar', true, true, 'MT_[size]_0,LT_[size]_2');
INSERT INTO aurora.entity_actions (id, code, created_at, updated_at, entity_code, text, multiple, existent, rules) VALUES (10, 'navigate_tab/[entity]', '2020-04-18 21:52:03', '2020-04-18 21:52:03','general', 'Adicionar', true, true, '');

INSERT INTO aurora.tab_configs (id, created_at, updated_at, code, name, entity_code) VALUES (11, '2020-03-26 18:57:56', '2020-03-26 18:57:58', 'tab', 'tab', 'general');
INSERT INTO aurora.tab_config_entries (id, created_at, updated_at, tab_config_id, title, code, entity_entry_code) VALUES (12, '2020-03-26 19:10:56', '2020-03-26 19:10:57', 11, 'General', 'general', 'general');
INSERT INTO aurora.tab_config_entry_components (id, created_at, updated_at, link, type, tab_config_entry_id, code) VALUES (10, '2020-03-26 19:13:18', '2020-03-26 19:13:19', 'product', 2, 12, 'general');

INSERT INTO aurora.form_configs (id, created_at, updated_at, entity_code, code) VALUES (13, '2020-03-27 20:26:05', '2020-03-27 20:26:06','general', 'general');

INSERT INTO aurora.hibernate_sequence (next_val) VALUES (14);