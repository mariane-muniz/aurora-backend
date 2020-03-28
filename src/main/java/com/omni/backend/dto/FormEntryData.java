package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormEntryData {
    private String code;
    private String text;
    private String placeHolder;
    private String label;
    private String helper;
    private String errorMessage;
    private String value;
    private String type;
}
