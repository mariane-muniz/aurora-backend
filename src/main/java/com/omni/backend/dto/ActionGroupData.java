package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ActionGroupData implements Data{
    private String name;
    private boolean displayPagination;
    private List<ActionData> actions = new ArrayList<>();
}