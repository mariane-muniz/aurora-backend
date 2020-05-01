package com.omni.backend.converters;

import com.omni.aurora.core.converters.AbstractConverter;
import com.omni.aurora.core.dto.EntityStructureEntryData;
import com.omni.aurora.core.populator.Populator;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.populator.EntityModelEntryPopulator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityEntryModelConverter extends AbstractConverter<EntityStructureEntryData, EntityEntryModel> {
    private final EntityModelEntryPopulator entityModelEntryPopulator;

    @Override
    protected List<Populator<EntityStructureEntryData, EntityEntryModel>> getPopulators() {
        final List<Populator<EntityStructureEntryData, EntityEntryModel>> list = new ArrayList<>();
        list.add(this.entityModelEntryPopulator);
        return list;
    }

    @Override
    protected EntityEntryModel getData() {
        return new EntityEntryModel();
    }
}