package com.omni.backend.facade;

import com.omni.backend.dto.TableActionGroupData;
import com.omni.backend.dto.TableData;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.parameter.TableParameter;
import javassist.NotFoundException;

import java.util.Set;

public interface TableFacade {
    TableData getTable(final TableParameter tableParameter) throws NotFoundException;

    Set<TableActionGroupData> getTableActions(RequestParameter parameter) throws NotFoundException;
}
