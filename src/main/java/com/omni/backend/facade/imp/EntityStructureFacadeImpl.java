package com.omni.backend.facade.imp;

import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.backend.facade.EntityStructureFacade;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.FarmModel;
import com.omni.backend.repository.FarmRepository;
import com.omni.backend.service.EntityService;
import com.omni.backend.service.FarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityStructureFacadeImpl implements EntityStructureFacade {

    private final FarmRepository farmRepository;
    private final FarmService farmService;
    private final EntityService entityService;

    @Override
    public void updateStructure(final String token) {
        final Iterable<FarmModel> farms = this.farmRepository.findAll();
        farms.forEach(farm -> {
            try {
                final String farmCode = farm.getCode();
                final ResponseEntity<EntityStructureData[]> entityStructures =
                        this.farmService.getEntityStructures(farmCode, token);

                if (HttpStatus.OK.equals(entityStructures.getStatusCode())) {
                    final EntityStructureData[] structures = entityStructures.getBody();
                    if (Objects.nonNull(structures)) {
                        for (final EntityStructureData structure : structures) {
                            final String code = structure.getCode();
                            final Optional<EntityModel> entity = this.entityService.findEntity(code);
                            if (entity.isPresent()) {
                                final EntityModel instance = entity.get();
                                if (!instance.getVersion().equals(structure.getVersion())) {
                                    this.entityService.removeEntity(instance);
                                    this.entityService.createEntity(structure, farm);
                                }
                            }
                            else this.entityService.createEntity(structure, farm);
                        }
                    }
                }
            }
            catch (URISyntaxException | ResourceAccessException e) {
                log.error(e.getMessage());
            }
        });
    }

    @Override
    public void removeStructures() {
        this.entityService.removeAllEntities();
    }
}
