package com.omni.backend.facade;

import com.omni.backend.dto.TableData;
import com.omni.backend.parameter.TableParameter;
import javassist.NotFoundException;

public interface TableFacade {
    TableData getTable(final TableParameter tableParameter) throws NotFoundException;
}