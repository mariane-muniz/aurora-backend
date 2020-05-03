package com.omni.backend.service.impl;

import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.backend.converters.EntityModelConverter;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.FarmModel;
import com.omni.backend.repository.EntityEntryRepository;
import com.omni.backend.repository.EntityRepository;
import com.omni.backend.service.EntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityServiceImpl implements EntityService {
    private final EntityRepository entityRepository;
    private final EntityModelConverter entityModelConverter;
    private final EntityEntryRepository entityEntryRepository;

    @Override
    public Optional<EntityModel> findEntity(final String code) {
        try {
            return this.entityRepository.findOneByCode(code);
        }
        catch (Exception e) {
         log.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void removeEntity(final EntityModel entity) {
        this.entityRepository.delete(entity);
    }

    @Override
    public void createEntity(final EntityStructureData structure, final FarmModel farm) {
        final EntityModel model = this.entityModelConverter.convert(structure);
        final Set<EntityEntryModel> attributes = model.getAttributes();
        model.setAttributes(null);
        model.setFarm(farm);
        this.entityRepository.save(model);
        attributes.forEach(entry -> {
            entry.setEntityCode(model.getCode());
            entry.setFarm(farm);
            entry.setEntity(model);
            entry.setCode(model.getCode() + "_" + entry.getCode());
            this.entityEntryRepository.save(entry);
        });
    }

    @Override
    public void removeAllEntities() {
        this.entityRepository.deleteAll();
    }
}