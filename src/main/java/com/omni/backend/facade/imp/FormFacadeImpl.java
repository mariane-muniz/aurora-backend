package com.omni.backend.facade.imp;

import com.omni.backend.dto.FormData;
import com.omni.backend.facade.FormFacade;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.FormConfigModel;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.populator.FormPopulator;
import com.omni.backend.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FormFacadeImpl implements FormFacade {
    private final EntityService entityService;
    private final FormPopulator formPopulator;

    @Override
    public FormData getForm(final RequestParameter parameter) {
        String entityCode = parameter.getEntityCode();
        final Optional<EntityModel> entity = this.entityService.findEntity(entityCode);
        if (entity.isPresent()) {
            final EntityModel instance = entity.get();
            // TODO impement strategy
            final FormConfigModel config =
                    instance.getFormConfigs().iterator().next();
            return this.formPopulator.populate(config, new FormData());
        }
        return null;
    }
}
