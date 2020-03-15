package com.omni.backend.repository;

import com.omni.backend.model.EntityModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "entity", itemResourceRel = "entities")
public interface EntityRepository extends PagingAndSortingRepository<EntityModel, Long> {
    Optional<EntityModel> findOneByCode(final String code);
}