package com.omni.backend.parameter;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestParameter extends TableParameter {
    private AbstractAudit config;
    private Object values;
}