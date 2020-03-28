package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPageData {
    private int size;
    private int totalElements;
    private int totalPages;
    private int number;
}