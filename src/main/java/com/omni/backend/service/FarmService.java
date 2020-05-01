package com.omni.backend.service;

import com.omni.backend.dto.ResponseData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.parameter.RestParameter;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

public abstract class FarmService {
    public abstract ResponseEntity<ResponseData> search(RestParameter parameter) throws URISyntaxException;

    public abstract ResponseEntity<String> getData(String farmCode, String entityCode,
                                                   String code, String token) throws URISyntaxException;

    public abstract Map<String, Object> getValues(Set<EntityEntryModel> entries, RequestParameter source);
}
