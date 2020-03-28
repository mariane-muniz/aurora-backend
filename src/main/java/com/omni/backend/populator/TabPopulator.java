package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.TabComponentData;
import com.omni.backend.dto.TabData;
import com.omni.backend.model.TabConfigEntryComponentModel;
import com.omni.backend.model.TabConfigEntryModel;
import com.omni.backend.model.TabConfigModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Service
public class TabPopulator implements Populator<TabConfigModel, Set<TabData>> {

    @Override
    public Set<TabData> populate(final TabConfigModel source, final Set<TabData> target) {
        final Set<TabConfigEntryModel> entries = source.getEntries();
        if (!CollectionUtils.isEmpty(entries)) {
            entries.stream().forEach(entry -> {
                final TabData entryData = new TabData();
                final Set<TabConfigEntryComponentModel> components = entry.getComponents();
                if (!CollectionUtils.isEmpty(components)) {
                    components.stream().forEach(component -> {
                        final TabComponentData componentData = new TabComponentData();
                        componentData.setLink(component.getLink());
                        componentData.setType(component.getType().name().toLowerCase());
                        entryData.getComponents().add(componentData);
                    });
                }
                entryData.setTitle(entry.getTitle());
                target.add(entryData);
            });
        }
        return target;
    }
}