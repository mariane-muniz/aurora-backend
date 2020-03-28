package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter
@Setter
public class ResponseData {
    private LinkedHashMap _embedded;
    private LinkedHashMap _links;
    private RequestPageData page;
}
