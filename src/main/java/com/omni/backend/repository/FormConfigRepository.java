package com.omni.backend.repository;

import com.omni.backend.model.FormConfigModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "form-config", itemResourceRel = "form-configs")
public interface FormConfigRepository extends PagingAndSortingRepository<FormConfigModel, Integer> {
    Optional<FormConfigModel> findOneByEntityCode(final String entityCode);
}