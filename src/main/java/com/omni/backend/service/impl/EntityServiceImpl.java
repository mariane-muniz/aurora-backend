package com.omni.backend.service.impl;

import com.omni.backend.model.EntityModel;
import com.omni.backend.repository.EntityRepository;
import com.omni.backend.service.EntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityServiceImpl implements EntityService {
    private final EntityRepository entityRepository;

    @Override
    public Optional<EntityModel> findEntity(final String code) {
        try {
            return this.entityRepository.findOneByCode(code);
        }
        catch (Exception e) {
         log.error(e.getMessage());
        }
        return Optional.empty();
    }
}