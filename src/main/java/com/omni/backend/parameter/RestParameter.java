package com.omni.backend.parameter;

import com.omni.backend.model.EntityModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestParameter implements Parameter {
    private EntityModel entity;
}
