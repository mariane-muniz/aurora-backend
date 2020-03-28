package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.FormData;
import com.omni.backend.dto.FormEntryData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.FormConfigModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FormPopulator implements Populator<FormConfigModel, FormData> {
    private final FormEntryPopulator formEntryPopulator;

    @Override
    public FormData populate(final FormConfigModel source, final FormData target) {
        // TODO to implement
//        target.setAlign(source.getAlign());
        final Set<EntityEntryModel> entries = source.getEntries();
        if (!CollectionUtils.isEmpty(entries)) {
            entries.stream().forEach(entry -> {
                final FormEntryData entryData =
                        this.formEntryPopulator.populate(entry, new FormEntryData());
                target.getEntries().add(entryData);
            });
        }
        return target;
    }
}
