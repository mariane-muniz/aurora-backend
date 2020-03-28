package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TabData {
    private String title;
    private Set<TabComponentData> components = new HashSet<>();
}