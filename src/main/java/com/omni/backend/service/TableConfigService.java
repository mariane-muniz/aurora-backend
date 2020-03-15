package com.omni.backend.service;

import com.omni.backend.model.TableConfigModel;

public interface TableConfigService {
    TableConfigModel get(String entityName);
}
