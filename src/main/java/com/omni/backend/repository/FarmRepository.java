package com.omni.backend.repository;

import com.omni.backend.model.FarmModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "farm", itemResourceRel = "farms")
public interface FarmRepository extends PagingAndSortingRepository<FarmModel, Long> {
    FarmModel findOneByName(final String name);
}