package com.omni.backend.repository;

import com.omni.backend.model.TabConfigEntryComponentModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tab-config-entry-component", itemResourceRel = "tab-config-entry-component")
public interface TabConfigEntryComponentRepository
        extends PagingAndSortingRepository<TabConfigEntryComponentModel, Integer> {
}
