package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.FormData;
import com.omni.backend.dto.FormEntryData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.FormConfigModel;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.service.EntityEntryService;
import com.omni.backend.service.FarmService;
import com.omni.backend.strategy.GroupEntryEntitiesStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FormPopulator implements Populator<RequestParameter, FormData> {
    private final FormEntryPopulator formEntryPopulator;
    private final GroupEntryEntitiesStrategy groupEntryEntitiesStrategy;
    private final FarmService farmService;
    private final EntityEntryService entityEntryService;

    @Override
    public FormData populate(final RequestParameter source, final FormData target) {
        final FormConfigModel config = (FormConfigModel) source.getConfig();
        if (Objects.nonNull(config)) {
            final List<String> entryCodes = config.getEntries();
            if (!CollectionUtils.isEmpty(entryCodes)) {
                final List<EntityEntryModel> entries = this.entityEntryService.getEntries(entryCodes);
                final Map<String, Object> values = this.farmService.getValues(entries, source);
                entries.forEach(entry -> {
                    final RequestParameter parameter = new RequestParameter();
                    parameter.setConfig(entry);
                    parameter.setValues(values);
                    final FormEntryData entryData = this.formEntryPopulator.populate(parameter, new FormEntryData());
                    target.getEntries().add(entryData);
                });
            }
        }
        return target;
    }
}