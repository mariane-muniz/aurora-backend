package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.TableData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.TableConfigModel;
import com.omni.backend.parameter.TableParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Service
public class TablePopulator implements Populator<TableParameter, TableData> {

    @Override
    public TableData populate(final TableParameter source, final TableData target) {
        Assert.notNull(source, "source");
        Assert.notNull(target, "target");

        final TableConfigModel config = source.getTableConfig();
        final Set<EntityEntryModel> entries = config.getEntries();

        target.setDisplayPagination(config.isDisplayPagination());

        this.setPublicNames(entries, target);

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

    private void getResults(final Set<EntityEntryModel> source, final TableData target) {
        if (!CollectionUtils.isEmpty(source)) {
        }
    }
}