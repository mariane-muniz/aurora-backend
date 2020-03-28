package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TableActionGroupData implements Data{
    private String name;
    private List<TableActionData> actions = new ArrayList<>();
}