package com.omni.backend.service;

import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.FarmModel;

import java.util.Optional;

public interface EntityService {
    Optional<EntityModel> findEntity(String code);
    void removeEntity(EntityModel entity);
    void createEntity(EntityStructureData structure, FarmModel farm);

    void removeAllEntities();
}