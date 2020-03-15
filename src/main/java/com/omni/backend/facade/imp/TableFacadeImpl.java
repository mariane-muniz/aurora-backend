package com.omni.backend.facade.imp;

import com.omni.backend.dto.TableData;
import com.omni.backend.facade.TableFacade;
import com.omni.backend.parameter.TableParameter;
import com.omni.backend.repository.EntityEntryModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TableFacadeImpl implements TableFacade {
    private final EntityEntryModelRepository entityEntryModelRepository;

    @Override
    public TableData getTable(final TableParameter tableParameter) {
//        this.entityEntryModelRepository.fin



        return new TableData();
    }
}