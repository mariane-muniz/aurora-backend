package com.omni.backend.service.impl;

import com.omni.backend.model.TabConfigModel;
import com.omni.backend.repository.TabConfigRepository;
import com.omni.backend.service.TabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TabServiceImpl implements TabService {
    private final TabConfigRepository tabConfigRepository;

    @Override
    public Optional<TabConfigModel> getConfig(final String entityCode) {
        Optional<TabConfigModel> config = this.tabConfigRepository.findOneByEntityCode(entityCode);
        if (!config.isPresent()) {
            config =  this.tabConfigRepository.findOneByEntityCode("general");
            return config;
        }
        else return config;
    }
}