package com.omni.backend.service;

import com.omni.backend.model.EntityModel;

import java.util.Optional;

public interface EntityService {
    Optional<EntityModel> findEntity(String code);
}
