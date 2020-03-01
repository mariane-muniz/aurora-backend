package com.omni.backend.repository;

import com.omni.backend.model.TableConfigModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "table_config", itemResourceRel = "table_configs")
public interface TableConfigRepository extends PagingAndSortingRepository<TableConfigModel, Long> {
}