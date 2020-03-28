package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableActionData implements Data{
    private String text;
    private String icon;
    private String link;
    private String btnType;
    private String condition;
    private String execute;
}
