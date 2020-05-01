package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.TabComponentData;
import com.omni.backend.dto.TabData;
import com.omni.backend.model.TabConfigEntryComponentModel;
import com.omni.backend.model.TabConfigEntryModel;
import com.omni.backend.model.TabConfigModel;
import com.omni.backend.parameter.RequestParameter;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class TabPopulator implements Populator<RequestParameter, Set<TabData>> {

    @Override
    public Set<TabData> populate(final RequestParameter source, final Set<TabData> target) {
        final TabConfigModel config = (TabConfigModel) source.getConfig();
        if (Objects.nonNull(config)) {
            Set<TabConfigEntryModel> entries = config.getEntries();
            if (!CollectionUtils.isEmpty(entries)) {
                this.selectTabs(entries, source).forEach(entry -> {
                    final TabData entryData = new TabData();
                    final Set<TabConfigEntryComponentModel> components = entry.getComponents();
                    if (!CollectionUtils.isEmpty(components)) {
                        final String code = source.getCode();
                        components.forEach(component -> this.populateEntry(component, entryData, code));
                    }
                    entryData.setTitle(entry.getTitle());
                    target.add(entryData);
                });
            }
        }
        return target;
    }

    private Set<TabConfigEntryModel> selectTabs(Set<TabConfigEntryModel> entries, final RequestParameter source) {
        if (StringUtils.isEmpty(source.getCode()) && !CollectionUtils.isEmpty(entries)) {
            for (final TabConfigEntryModel entry : entries) {
                if (entry.getCode().contains("general")) {
                    return new HashSet<>(Collections.singleton(entry));
                }
            }
        }
        return entries;
    }

    private void populateEntry(final TabConfigEntryComponentModel component,
                               final TabData entryData, final String code) {
        final TabComponentData componentData = new TabComponentData();
        componentData.setLink(component.getLink());
        componentData.setType(component.getType().name().toLowerCase());
        if (StringUtils.isNotEmpty(code)) {
            componentData.setLink(componentData.getLink() + "/" + code);
        }
        entryData.getComponents().add(componentData);
    }
}