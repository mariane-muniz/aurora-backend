package com.omni.backend.service.impl;

import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.repository.EntityEntryRepository;
import com.omni.backend.service.EntityEntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityEntryServiceImpl implements EntityEntryService {
    private final EntityEntryRepository entityEntryRepository;

    @Override
    public List<EntityEntryModel> getEntries(final List<String> entryCodes) {
        return this.entityEntryRepository.findByCode(entryCodes);
    }

    @Override
    public List<EntityEntryModel> getEntries(final String entityCode) {
        return this.entityEntryRepository.findByEntityCode(entityCode);
    }
}