package com.omni.backend.controller;

import com.omni.backend.dto.TabData;
import com.omni.backend.facade.TabFacade;
import com.omni.backend.parameter.RequestParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class EntityTabController {
    private final TabFacade tabFacade;

    @GetMapping(
            path = "/tab/{entityName}/{code}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity getTab(final @PathVariable("entityName") String entityName,
                                   final @PathVariable(name = "code", required = false) String code,
                                   final @RequestHeader Map<String, String> headers) {
        try {
            final RequestParameter parameter = new RequestParameter();
            parameter.setEntityCode(entityName);
            parameter.setCode(code);
            parameter.setToken(headers.get("authorization"));
            final Set<TabData> tabData = this.tabFacade.getTab(parameter);
            if (Objects.nonNull(tabData))
                return ResponseEntity.status(HttpStatus.OK).body(tabData);
            else
                return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            log.error("The response was sent empty.");
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/tab/{entityName}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity getTabEntity(final @PathVariable("entityName") String entityName,
                                   final @RequestHeader Map<String, String> headers) {
        return this.getTab(entityName, null, headers);
    }
}