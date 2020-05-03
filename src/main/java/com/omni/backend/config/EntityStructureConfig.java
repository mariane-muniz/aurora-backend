package com.omni.backend.config;

import com.omni.backend.facade.EntityStructureFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityStructureConfig {
    private final EntityStructureFacade entityStructureFacade;

    @Scheduled(initialDelay = 10000L, fixedRate = 60000000)
    protected void createEntityStructure() {
        log.info("Start structure sync...");
        this.entityStructureFacade.removeStructures();
        this.entityStructureFacade.updateStructure(null);
        log.info("End structure sync.");
    }
}