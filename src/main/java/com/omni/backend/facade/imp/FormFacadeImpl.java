package com.omni.backend.facade.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omni.backend.dto.FarmMessageData;
import com.omni.backend.dto.FormData;
import com.omni.backend.facade.FormFacade;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.FarmModel;
import com.omni.backend.model.FormConfigModel;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.populator.FormPopulator;
import com.omni.backend.service.EntityService;
import com.omni.backend.service.FarmService;
import com.omni.backend.service.FormConfigService;
import com.omni.backend.service.MessageQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FormFacadeImpl implements FormFacade {

    private final EntityService entityService;
    private final FormPopulator formPopulator;
    private final FarmService farmService;
    private final FormConfigService formConfigService;
    private final MessageQueueService messageQueueService;

    @Override
    public FormData getForm(final RequestParameter parameter) {
        final String entityCode = parameter.getEntityCode();
        final Optional<EntityModel> entity = this.entityService.findEntity(entityCode);
        if (entity.isPresent()) {
            final FormConfigModel config = this.formConfigService.getForm(entityCode);
            if (Objects.nonNull(config)) {
                parameter.setConfig(config);
                return this.formPopulator.populate(parameter, new FormData());
            }
        }
        return null;
    }

    @Override
    public void registerForm(final String jsonForm, final RequestParameter parameter)  {
        final String entityCode = parameter.getEntityCode();
        final Optional<EntityModel> entity = this.entityService.findEntity(entityCode);
        if (entity.isPresent()) {
            try {
                this.encapsulate(entityCode, jsonForm, "CREATE");
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteForm(final RequestParameter parameter)  {
        final String entityCode = parameter.getEntityCode();
        final Optional<EntityModel> entity = this.entityService.findEntity(entityCode);
        entity.ifPresent(instance -> {
            try {
                final FarmModel farm = instance.getFarm();
                final String farmCode = farm.getCode();
                final String code = parameter.getCode();
                final String token = parameter.getToken();
                final ResponseEntity<String> response =
                        this.farmService.getData(farmCode, entityCode, code, token);
                if (HttpStatus.OK.equals(response.getStatusCode())) {
                    try {
                        final class Delete {
                            public String code;
                            Delete(final String code) {
                                this.code = code;
                            }
                        }
                        final ObjectMapper mapper = new ObjectMapper();
                        final String json = mapper.writeValueAsString(new Delete(code));
                        this.encapsulate(entityCode, json, "DELETE");
                    }
                    catch (JsonProcessingException e) {
                        log.error(e.getMessage());
                    }
                }
            }
            catch (URISyntaxException e) {
                log.error(e.getMessage());
            }
        });
    }

    @Override
    public void updateForm(final String jsonForm, final RequestParameter parameter)  {
        final String entityCode = parameter.getEntityCode();
        final Optional<EntityModel> entity = this.entityService.findEntity(entityCode);
        entity.ifPresent(instance -> {
            final FarmModel farm = instance.getFarm();
            final String farmCode = farm.getCode();
            final String code = parameter.getCode();
            final String token = parameter.getToken();
            try
            {
                final ResponseEntity<String> response =
                        this.farmService.getData(farmCode, entityCode, code, token);
                if (HttpStatus.OK.equals(response.getStatusCode())) {
                    try
                    {
                        this.encapsulate(entityCode, jsonForm, "UPDATE");
                    }
                    catch (JsonProcessingException e) {
                        log.error(e.getMessage());
                    }
                }
            }
            catch (URISyntaxException e) {
                log.error(e.getMessage());
            }
        });
    }

    private void encapsulate(
            final String entityCode, final String jsonForm,
            final String action) throws JsonProcessingException {
        // TODO implement obj validation
        final ObjectMapper mapper = new ObjectMapper();
        final FarmMessageData dto = new FarmMessageData();
        dto.setType(entityCode);
        dto.setAction(action);
        dto.setContent(jsonForm);
        final String stringValue = mapper.writeValueAsString(dto);
        this.messageQueueService.send(stringValue);
    }
}
