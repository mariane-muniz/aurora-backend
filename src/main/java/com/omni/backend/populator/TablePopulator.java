package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.TableData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.TableConfigModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Service
public class TablePopulator implements Populator<TableConfigModel, TableData> {

    @Override
    public TableData populate(final TableConfigModel source, final TableData target) {
        target.setDisplayPagination(source.isDisplayPagination());
        this.setPublicNames(source, target);
        return target;
    }

    private void setPublicNames(final TableConfigModel source, final TableData target) {
        final Set<EntityEntryModel> entries = source.getEntries();
        if (!CollectionUtils.isEmpty(entries)) {
            entries.forEach(entityEntryModel -> {
                target.getFields().add(entityEntryModel.getPublicName());
            });
        }
    }
}