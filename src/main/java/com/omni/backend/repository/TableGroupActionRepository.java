package com.omni.backend.repository;

import com.omni.backend.model.TableGroupActionModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "table-group-action", itemResourceRel = "table-group-actions")
public interface TableGroupActionRepository extends PagingAndSortingRepository<TableGroupActionModel, Integer> {
}