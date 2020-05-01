package com.omni.backend.populator;

import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.aurora.core.dto.EntityStructureEntryData;
import com.omni.aurora.core.populator.Populator;
import com.omni.backend.converters.EntityEntryModelConverter;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.EntityModel;
import com.omni.backend.repository.EntityEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityModelPopulator implements Populator<EntityStructureData, EntityModel> {
    private final EntityEntryModelConverter entityEntryModelConverter;
    private final EntityEntryRepository entityEntryRepository;

    @Override
    public EntityModel populate(final EntityStructureData source, final EntityModel target) {
        final List<EntityStructureEntryData> entries = source.getEntries();
        final List<EntityEntryModel> entryModels = this.entityEntryModelConverter.convertAll(entries);
        Set<EntityEntryModel> set = new HashSet<>(entryModels);
        target.setAttributes(set);
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setVersion(source.getVersion());
        return target;
    }
}