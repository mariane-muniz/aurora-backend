package com.omni.backend.converters;

import com.omni.aurora.core.converters.AbstractConverter;
import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.aurora.core.populator.Populator;
import com.omni.backend.model.EntityModel;
import com.omni.backend.populator.EntityModelPopulator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityModelConverter extends AbstractConverter<EntityStructureData, EntityModel> {
    private final EntityModelPopulator entityModelPopulator;

    @Override
    protected List<Populator<EntityStructureData, EntityModel>> getPopulators() {
        final List<Populator<EntityStructureData, EntityModel>> list = new ArrayList<>();
        list.add(this.entityModelPopulator);
        return list;
    }

    @Override
    protected EntityModel getData() {
        return new EntityModel();
    }
}