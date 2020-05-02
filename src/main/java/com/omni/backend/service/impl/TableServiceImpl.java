package com.omni.backend.service.impl;

import com.omni.backend.model.TableConfigModel;
import com.omni.backend.repository.TableConfigRepository;
import com.omni.backend.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TableServiceImpl implements TableService {
    private final TableConfigRepository tableConfigRepository;

    @Override
    public TableConfigModel getTableConfig(final String entityCode) {
        return this.tableConfigRepository.findByEntityCode(entityCode);
    }
}