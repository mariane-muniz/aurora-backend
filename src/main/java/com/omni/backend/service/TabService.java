package com.omni.backend.service;

import com.omni.backend.model.TabConfigModel;

public interface TabService {
    TabConfigModel getConfig(String entityCode);
}