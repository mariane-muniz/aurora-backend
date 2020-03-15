package com.omni.backend.service.impl;

import com.omni.backend.dao.TableConfigDao;
import com.omni.backend.model.TableConfigModel;
import com.omni.backend.service.TableConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TableConfigServiceImpl implements TableConfigService {
    private final TableConfigDao tableConfigDao;

    @Override
    public TableConfigModel get(final String entityName) {
        try {
            return this.tableConfigDao.getConfig(entityName);
        }
        catch (NonUniqueResultException | NoResultException e) {
            log.warn("Not found entity with name: " + entityName);
        }
        return null;
    }
}