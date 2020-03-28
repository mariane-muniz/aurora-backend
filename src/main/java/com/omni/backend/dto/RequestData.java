package com.omni.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestData {
    private RequestPageData page;
    private List<Object> _embedded;
}