package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FormData {
    private String align;
    private List<FormEntryData> entries = new ArrayList<>();
}
