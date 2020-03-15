package com.omni.backend.dao;

import com.omni.backend.model.TableConfigModel;

public interface TableConfigDao {
    TableConfigModel getConfig(final String entityName);
}
