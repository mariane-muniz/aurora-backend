package com.omni.backend.facade;

import com.omni.backend.dto.TabData;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.parameter.TableParameter;

import java.util.Set;

public interface TabFacade {
    Set<TabData> getTab(RequestParameter parameter);
}
