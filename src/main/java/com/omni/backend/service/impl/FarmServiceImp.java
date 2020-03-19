package com.omni.backend.service.impl;

import com.omni.backend.model.EntityModel;
import com.omni.backend.model.FarmModel;
import com.omni.backend.parameter.RestParameter;
import com.omni.backend.service.FarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FarmServiceImp extends FarmService {
    @Value(value = "${app.protocol}")
    private String protocol;
    private final RestTemplate restTemplate;

    @Override
    public void search(final RestParameter parameter) {
        final EntityModel entity = parameter.getEntity();
        final String entityCode = entity.getCode();
        final FarmModel farm = entity.getFarm();
        final String farmCode = farm.getCode();
        final String URL = this.protocol + farmCode + "/" + entityCode;
        log.info("Farm URL: " + URL);
        falta colocar o header com o token
        Object objReturn = this.restTemplate.getForObject(URL, Object.class);
        log.info(objReturn.toString());
    }
}