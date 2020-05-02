package com.omni.backend.strategy;

import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.FarmModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupEntryEntitiesStrategy {

    public Map<String, Set<String>> convert(final List<EntityEntryModel> source) {
        Map<String, Set<String>> response = new HashMap<>();
        source.forEach(entry -> {
            final FarmModel farm = entry.getFarm();
            final String farmCode = farm.getCode();
            if (StringUtils.isNotBlank(farmCode)) {
                if (!response.containsKey(farmCode)) {
                    response.put(farmCode, new HashSet<>());
                }
            }
        });
        return response;
    }
}