package com.omni.backend.repository;

import com.omni.backend.model.EntityModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "entity", itemResourceRel = "entities")
public interface EntityModelRepository extends PagingAndSortingRepository<EntityModel, Long> {
}