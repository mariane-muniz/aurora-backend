package com.omni.backend.parameter;

import com.omni.backend.model.EntityModel;
import com.omni.backend.model.TableConfigModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableParameter implements Parameter{
    private String entityCode;
    private EntityModel entity;
    private TableConfigModel tableConfig;
    private String token;
}