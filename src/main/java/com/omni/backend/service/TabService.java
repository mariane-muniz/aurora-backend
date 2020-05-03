package com.omni.backend.service;

import com.omni.backend.model.TabConfigModel;

import java.util.Optional;

public interface TabService {
    Optional<TabConfigModel> getConfig(String entityCode);
}