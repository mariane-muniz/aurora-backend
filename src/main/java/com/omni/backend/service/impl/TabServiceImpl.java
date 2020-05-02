package com.omni.backend.service.impl;

import com.omni.backend.model.TabConfigModel;
import com.omni.backend.repository.TabConfigRepository;
import com.omni.backend.service.TabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TabServiceImpl implements TabService {
    private final TabConfigRepository tabConfigRepository;

    @Override
    public TabConfigModel getConfig(final String entityCode) {
        return this.tabConfigRepository.findOneByEntityCode(entityCode);
    }
}