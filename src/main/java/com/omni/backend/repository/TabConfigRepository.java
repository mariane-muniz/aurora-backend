package com.omni.backend.repository;

import com.omni.backend.model.TabConfigModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tab-config", itemResourceRel = "tab-configs")
public interface TabConfigRepository extends PagingAndSortingRepository<TabConfigModel, Integer> {
    TabConfigModel findOneByEntityCode(final String entityCode);
}