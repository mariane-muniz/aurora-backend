package com.omni.backend.facade;

import com.omni.backend.dto.FormData;
import com.omni.backend.parameter.RequestParameter;

public interface FormFacade {
    FormData getForm(RequestParameter parameter);
}
