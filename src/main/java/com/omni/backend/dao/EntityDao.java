package com.omni.backend.dao;

import com.omni.backend.model.EntityModel;

import java.util.Optional;

public interface EntityDao {
    Optional<EntityModel> get();
}
