package com.omni.backend.repository;

import com.omni.backend.model.EntityEntryModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "entity_entry", itemResourceRel = "entity_entries")
public interface EntityEntryRepository extends PagingAndSortingRepository<EntityEntryModel, Long> {

    @Query("SELECT entry FROM EntityEntryModel entry WHERE entry.code IN(:codes)")
    List<EntityEntryModel> findByCode(final List<String> codes);
}