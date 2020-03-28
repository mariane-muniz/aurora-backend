package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TableData implements Data {
    private boolean displayPagination;
    private List<TableActionGroupData> actionGroups = new ArrayList<>();
    private List<String> fields = new ArrayList<>();
    private List<String> selected = new ArrayList<>();
    private List<ArrayList> values = new ArrayList<>();
}