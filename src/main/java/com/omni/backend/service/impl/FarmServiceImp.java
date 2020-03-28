package com.omni.backend.service.impl;

import com.omni.backend.dto.ResponseData;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.FarmModel;
import com.omni.backend.parameter.RestParameter;
import com.omni.backend.service.FarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FarmServiceImp extends FarmService {
    @Value(value = "${app.protocol}")
    private String protocol;
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<ResponseData> search(final RestParameter parameter) throws URISyntaxException {
        final EntityModel entity = parameter.getEntity();
        final String entityCode = entity.getCode();
        final FarmModel farm = entity.getFarm();
        final String farmCode = farm.getCode();
        final URI URL = new URI(this.protocol + farmCode + "/" + entityCode);
        final HttpEntity<Object> headers = this.getHeader(parameter.getToken());
        return restTemplate.exchange(URL, HttpMethod.GET, headers, ResponseData.class);
    }

    private HttpEntity<Object> getHeader(final String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);
        return new HttpEntity<>(headers);
    }
}