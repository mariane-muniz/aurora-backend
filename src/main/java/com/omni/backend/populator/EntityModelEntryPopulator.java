package com.omni.backend.populator;

import com.omni.aurora.core.dto.EntityStructureEntryData;
import com.omni.aurora.core.populator.Populator;
import com.omni.backend.model.EntityEntryModel;
import org.springframework.stereotype.Service;


@Service
public class EntityModelEntryPopulator implements Populator<EntityStructureEntryData, EntityEntryModel> {

    @Override
    public EntityEntryModel populate(final EntityStructureEntryData source, final EntityEntryModel target) {
        target.setType(source.getType());
        target.setCode(source.getName() + "_" + source.getType());
        return target;
    }
}