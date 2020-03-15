package com.omni.backend.facade.imp;

import com.omni.backend.dto.TableData;
import com.omni.backend.facade.TableFacade;
import com.omni.backend.model.TableConfigModel;
import com.omni.backend.parameter.TableParameter;
import com.omni.backend.populator.TablePopulator;
import com.omni.backend.service.TableConfigService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TableFacadeImpl implements TableFacade {
    private final TableConfigService tableConfigService;
    private final TablePopulator tablePopulator;

    @Override
    public TableData getTable(final TableParameter tableParameter) throws NotFoundException {
        Assert.notNull(tableParameter.getEntityName(), "entityName");
        final TableData data = new TableData();
        final TableConfigModel config = this.tableConfigService.get(tableParameter.getEntityName());
        if (Objects.nonNull(config)) {
            return this.tablePopulator.populate(config, data);
        }
        throw new NotFoundException(String.format("Entity name '%s' not found", tableParameter.getEntityName()));
    }
}