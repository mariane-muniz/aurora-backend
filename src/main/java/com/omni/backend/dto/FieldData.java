package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FieldData {
    private Set<String> publicName = new HashSet<>();
}