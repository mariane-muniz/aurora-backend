package com.omni.backend.service;

import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.backend.dto.ResponseData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.FarmModel;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.parameter.RestParameter;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public abstract class FarmService {
    public abstract FarmModel getFarm(String name);

    public abstract ResponseEntity<EntityStructureData[]> getEntityStructures(
            String farmCode,
            String token) throws URISyntaxException;
    public abstract ResponseEntity<ResponseData> search(RestParameter parameter) throws URISyntaxException;
    public abstract ResponseEntity<String> getData(String farmCode, String entityCode,
                                                   String code, String token) throws URISyntaxException;
    public abstract Map<String, Object> getValues(List<EntityEntryModel> entries, RequestParameter source);
}