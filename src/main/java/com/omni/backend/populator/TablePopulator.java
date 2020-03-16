package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.TableData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.TableConfigModel;
import com.omni.backend.parameter.RestParameter;
import com.omni.backend.parameter.TableParameter;
import com.omni.backend.service.FarmService;
import com.omni.backend.strategy.GroupEntryEntitiesStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TablePopulator implements Populator<TableParameter, TableData> {
    private final FarmService farmService;
    private final GroupEntryEntitiesStrategy groupEntryEntitiesStrategy;

    @Override
    public TableData populate(final TableParameter source, final TableData target) {
        Assert.notNull(source, "source");
        Assert.notNull(target, "target");

        final TableConfigModel config = source.getTableConfig();
        final EntityModel entity = source.getEntity();
        final Set<EntityEntryModel> entries = config.getEntries();

        target.setDisplayPagination(config.isDisplayPagination());
        this.setPublicNames(entries, target);
        this.setMainResult(entity, target);
        this.setSlaveResults(entries, target);

        return target;
    }

    private void setPublicNames(final Set<EntityEntryModel> source, final TableData target) {
        Assert.notNull(source, "source");
        Assert.notNull(target, "target");
        if (!CollectionUtils.isEmpty(source)) {
            source.forEach(entityEntryModel -> {
                target.getFields().add(entityEntryModel.getPublicName());
            });
        }
    }

    private void setMainResult(final EntityModel entity, final TableData target) {
        final RestParameter parameter = new RestParameter();
        parameter.setEntity(entity);
        this.farmService.search(parameter);
    }

    private void setSlaveResults(final Set<EntityEntryModel> source, final TableData target) {
        if (!CollectionUtils.isEmpty(source)) {
            Map<String, Set<String>> mapping = this.groupEntryEntitiesStrategy.convert(source);
        }
    }
}