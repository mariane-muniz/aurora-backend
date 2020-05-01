package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.FormEntryData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.parameter.RequestParameter;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class FormEntryPopulator implements Populator<RequestParameter, FormEntryData> {

    @Override
    public FormEntryData populate(final RequestParameter source, final FormEntryData target) {
        final EntityEntryModel config = (EntityEntryModel) source.getConfig();
        final Map<String, Object> values = (Map<String, Object>) source.getValues();

        target.setCode(config.getName());
        target.setLabel(config.getPublicName());
        target.setType(config.getType().name().toLowerCase());
        // TODO to implements on the model
//        target.setHelper();
        this.setValue(target, values);
        return target;
    }

    private void setValue(final FormEntryData target, final Map<String, Object> values) {
        final String code = target.getCode();
        if (Objects.nonNull(values) && values.containsKey(code)) {
            target.setValue(values.get(code).toString());
        }
    }
}