package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.FormEntryData;
import com.omni.backend.model.EntityEntryModel;
import org.springframework.stereotype.Service;

@Service
public class FormEntryPopulator implements Populator<EntityEntryModel, FormEntryData> {

    @Override
    public FormEntryData populate(final EntityEntryModel source, final FormEntryData target) {
        target.setCode(source.getName());
        target.setLabel(source.getPublicName());
        target.setType(source.getType().name().toLowerCase());
        // TODO to implements on the model
//        target.setHelper();
        this.setValue(target);
        return target;
    }

    //TODO to implement request
    private void setValue(final FormEntryData target) {
//        target.setValue();
    }
}
