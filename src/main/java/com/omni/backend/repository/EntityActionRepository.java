package com.omni.backend.repository;

import com.omni.backend.model.EntityActionModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Set;

@RepositoryRestResource(path = "entity-action", itemResourceRel = "entity-actions")
public interface EntityActionRepository extends PagingAndSortingRepository<EntityActionModel, Integer> {

    @RestResource(path = "find-by-entity")
    @Query(
            "SELECT AC FROM EntityActionModel as AC " +
            "WHERE AC.entityCode = :entity " +
                "AND AC.existent = :existent " +
                "AND AC.multiple = :multiple"
    )
    Set<EntityActionModel> findByEntity(
            @Param(value = "entity") final String entity,
            @Param(value = "multiple") final boolean multiple,
            @Param(value = "existent") final boolean existent
    );
}