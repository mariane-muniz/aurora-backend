package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmMessageData {
    private String content;
    private String type;
    private String action;
}