package com.omni.backend.service;

import com.omni.backend.model.TableConfigModel;

public interface TableService {
    TableConfigModel getTableConfig(String entityCode);
}