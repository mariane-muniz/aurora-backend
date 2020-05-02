package com.omni.backend.service;

import com.omni.backend.model.FormConfigModel;

public interface FormConfigService {
    FormConfigModel getForm(String entityCode);
}
