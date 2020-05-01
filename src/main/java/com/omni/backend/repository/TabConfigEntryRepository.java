package com.omni.backend.repository;

import com.omni.backend.model.TabConfigEntryModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tab-config-entry", itemResourceRel = "tab-config-entries")
public interface TabConfigEntryRepository extends PagingAndSortingRepository<TabConfigEntryModel, Integer> {
}
