package com.omni.backend.repository;

import com.omni.backend.model.TableConfigModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tab-config", itemResourceRel = "tab-configs", exported = true)
public interface TabConfigRepository extends PagingAndSortingRepository<TableConfigModel, Integer> {
}