package com.omni.backend.facade.imp;

import com.omni.backend.dto.TabData;
import com.omni.backend.facade.TabFacade;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.TabConfigModel;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.populator.TabPopulator;
import com.omni.backend.service.EntityService;
import com.omni.backend.service.TabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class TabFacadeImpl implements TabFacade {
    private final TabPopulator tabPopulator;
    private final EntityService entityService;
    private final TabService tabService;

    @Override
    public Set<TabData> getTab(final RequestParameter parameter) {
        try {
            final String entityCode = parameter.getEntityCode();
            final Optional<EntityModel> entity = this.entityService.findEntity(entityCode);
            if (entity.isPresent()) {
                final TabConfigModel tabConfig = this.tabService.getConfig(entityCode);
                parameter.setConfig(tabConfig);
                return this.tabPopulator.populate(parameter, new HashSet<>());
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}