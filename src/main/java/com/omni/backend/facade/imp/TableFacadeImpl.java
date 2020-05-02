package com.omni.backend.facade.imp;

import com.omni.backend.dto.TableActionGroupData;
import com.omni.backend.dto.TableData;
import com.omni.backend.facade.TableFacade;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.TableConfigModel;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.parameter.TableParameter;
import com.omni.backend.populator.TableActionGroupPopulator;
import com.omni.backend.populator.TablePopulator;
import com.omni.backend.service.EntityService;
import com.omni.backend.service.TableService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TableFacadeImpl implements TableFacade {
    private final TablePopulator tablePopulator;
    private final TableActionGroupPopulator tableActionGroupPopulator;
    private final EntityService entityService;
    private final TableService tableService;


    @Override
    public TableData getTable(final TableParameter tableParameter) throws NotFoundException {
        Assert.notNull(tableParameter.getEntityCode(), "entityName");
        final String entityCode = tableParameter.getEntityCode();
        final Optional<EntityModel> entity = this.entityService.findEntity(entityCode);
        if (entity.isPresent()) {
            final TableData data = new TableData();
            final EntityModel entityObject = entity.get();
            final TableConfigModel tableConfig = this.tableService.getTableConfig(entityCode);
            if(Objects.nonNull(tableConfig)) {
                tableParameter.setEntity(entityObject);
                tableParameter.setTableConfig(tableConfig);
                return this.tablePopulator.populate(tableParameter, data);
            }
        }
        throw new NotFoundException(String.format("Entity name '%s' not found", tableParameter.getEntityCode()));
    }

    @Override
    public final Set<TableActionGroupData> getTableActions(final RequestParameter parameter) throws NotFoundException {
        Assert.notNull(parameter.getEntityCode(), "entityCode");
        final String entityCode = parameter.getEntityCode();
        final Optional<EntityModel> entity = this.entityService.findEntity(entityCode);
        if (entity.isPresent()) {
            final TableConfigModel tableConfig = this.tableService.getTableConfig(entityCode);
            return this.tableActionGroupPopulator.populate(tableConfig, new HashSet<>());
        }
        throw new NotFoundException(String.format("Entity name '%s' not found", parameter.getEntityCode()));
    }
}