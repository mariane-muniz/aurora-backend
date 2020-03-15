package com.omni.backend.facade.imp;

import com.omni.backend.dto.TableData;
import com.omni.backend.facade.TableFacade;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.TableConfigModel;
import com.omni.backend.parameter.TableParameter;
import com.omni.backend.populator.TablePopulator;
import com.omni.backend.service.EntityService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TableFacadeImpl implements TableFacade {
    private final TablePopulator tablePopulator;
    private final EntityService entityService;

    @Override
    public TableData getTable(final TableParameter tableParameter) throws NotFoundException {
        Assert.notNull(tableParameter.getEntityCode(), "entityName");
        final Optional<EntityModel> entity = this.entityService.findEntity(tableParameter.getEntityCode());
        if (entity.isPresent()) {
            final TableData data = new TableData();
            final EntityModel entityObject = entity.get();
            final Set<TableConfigModel> configs = entityObject.getConfigs();
            if(!CollectionUtils.isEmpty(configs)) {
                // TODO implement config select strategy
                TableConfigModel config = configs.iterator().next();
                tableParameter.setEntity(entityObject);
                tableParameter.setTableConfig(config);
                return this.tablePopulator.populate(tableParameter, data);
            }
        }
        throw new NotFoundException(String.format("Entity name '%s' not found", tableParameter.getEntityCode()));
    }
}