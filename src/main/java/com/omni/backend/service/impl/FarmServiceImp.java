package com.omni.backend.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.backend.dto.ResponseData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.FarmModel;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.parameter.RestParameter;
import com.omni.backend.repository.FarmRepository;
import com.omni.backend.service.FarmService;
import com.omni.backend.strategy.GroupEntryEntitiesStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FarmServiceImp extends FarmService {

    @Value(value = "${app.protocol}")
    private String protocol;
    private final RestTemplate restTemplate;
    private final FarmRepository farmRepository;
    private final GroupEntryEntitiesStrategy groupEntryEntitiesStrategy;

    @Override
    public FarmModel getFarm(final String name) {
        return this.farmRepository.findOneByName(name);
    }

    @Override
    public ResponseEntity<EntityStructureData[]> getEntityStructures(
            final String farmCode,
            final String token) throws URISyntaxException {
        final URI URL = new URI(this.protocol + farmCode + "/entity-structure");
        final HttpEntity<Object> headers = this.getHeader(token);
        return restTemplate.exchange(URL, HttpMethod.GET, headers, EntityStructureData[].class);
    }

    @Override
    public ResponseEntity<ResponseData> search(final RestParameter parameter) throws URISyntaxException {
        Assert.notNull(parameter, "parameter");
        final EntityModel entity = parameter.getEntity();
        final String entityCode = entity.getCode();
        final FarmModel farm = entity.getFarm();
        final String farmCode = farm.getCode();
        final URI URL = new URI(this.protocol + farmCode + "/" + entityCode);
        final HttpEntity<Object> headers = this.getHeader(parameter.getToken());
        return restTemplate.exchange(URL, HttpMethod.GET, headers, ResponseData.class);
    }

    @Override
    public ResponseEntity<String> getData(final String farmCode, final String entityCode,
                                          final String code, final String token) throws URISyntaxException {
        Assert.notNull(farmCode, "farmCode");
        Assert.notNull(farmCode, "entityCode");
        Assert.notNull(farmCode, "code");
        Assert.notNull(token, "token");
        final URI URL = new URI(this.protocol + farmCode + "/" + entityCode + "/search/code?value=" + code);
        final HttpEntity<Object> headers = this.getHeader(token);
        return restTemplate.exchange(URL, HttpMethod.GET, headers, String.class);
    }

    private HttpEntity<Object> getHeader(final String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);
        return new HttpEntity<>(headers);
    }

    @Override
    public Map<String, Object> getValues(final List<EntityEntryModel> entries, final RequestParameter source) {
        if (!CollectionUtils.isEmpty(entries) && StringUtils.isNotEmpty(source.getCode())) {
            final Map<String, Object> data = new HashMap<>();
            final Map<String, Set<String>> strategies = this.groupEntryEntitiesStrategy.convert(entries);
            final String code = source.getCode();
            final String entityCode = source.getEntityCode();
            final String token = source.getToken();
            final ObjectMapper mapper = new ObjectMapper();
            strategies.keySet().iterator().forEachRemaining(farm -> {
                try {
                    Set<String> attrNames = strategies.get(farm);
                    ResponseEntity<String> result = this.getData(farm, entityCode, code, token);
                    if(result.getStatusCode().equals(HttpStatus.OK) && StringUtils.isNotEmpty(result.getBody())) {
                        final Map map = mapper.readValue(result.getBody(), Map.class);
                        attrNames.iterator().forEachRemaining(attrName ->
                                data.put(attrName, map.get(attrName))
                        );
                    }
                }
                catch (URISyntaxException | JsonProcessingException e) {
                    log.error(e.getMessage());
                }
            });
            return data;
        }
        return null;
    }
}