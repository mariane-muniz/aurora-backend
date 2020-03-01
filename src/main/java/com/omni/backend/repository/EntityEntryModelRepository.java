package com.omni.backend.repository;

import com.omni.backend.model.EntityEntryModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "entity_entry", itemResourceRel = "entity_entries")
public interface EntityEntryModelRepository extends PagingAndSortingRepository<EntityEntryModel, Long> {
}