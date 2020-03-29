package com.omni.backend.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.omni.backend.dto.FormData;
import com.omni.backend.parameter.RequestParameter;

public interface FormFacade {
    FormData getForm(RequestParameter parameter);
    void registerForm(String jsonForm, RequestParameter parameter) throws JsonProcessingException;
}