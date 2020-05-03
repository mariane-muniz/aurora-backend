package com.omni.backend.service;

import com.omni.backend.model.EntityEntryModel;

import java.util.List;

public interface EntityEntryService {
    List<EntityEntryModel> getEntries(List<String> entryCodes);

    List<EntityEntryModel> getEntries(String entityCode);
}