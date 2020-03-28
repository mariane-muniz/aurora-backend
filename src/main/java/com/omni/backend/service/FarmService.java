package com.omni.backend.service;

import com.omni.backend.dto.ResponseData;
import com.omni.backend.parameter.RestParameter;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public abstract class FarmService {
    public abstract ResponseEntity<ResponseData> search(RestParameter parameter) throws URISyntaxException;
}
