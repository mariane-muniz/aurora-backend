package com.omni.backend.controller;

import com.omni.backend.dto.FormData;
import com.omni.backend.facade.FormFacade;
import com.omni.backend.parameter.RequestParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class EntityFormController {

    private static final Logger LOG = Logger.getLogger(EntityTableController.class.getName());
    private final FormFacade formFacade;

    @GetMapping(
            path = "/form/{entityName}/{code}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity getFormEntityWithValues(
            final @PathVariable("entityName") String entityName,
            final @PathVariable String code,
            final @RequestHeader Map<String, String> headers) {
        try
        {
            final RequestParameter parameter = new RequestParameter();
            parameter.setEntityCode(entityName);
            parameter.setCode(code);
            parameter.setToken(headers.get("authorization"));
            final FormData data = this.formFacade.getForm(parameter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
        }
        catch (Exception e)
        {
            LOG.log(Level.SEVERE, "The response was sent empty.");
            LOG.log(Level.SEVERE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/form/{entityName}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity getFormEntity(
            final @PathVariable("entityName") String entityName,
            final @RequestHeader Map<String, String> headers) {
        return this.getFormEntityWithValues(entityName, null, headers);
    }

    @PostMapping(
            path = "/form/{entityName}",
            headers = "Content-Type=application/json",
            produces = "application/json")
    public ResponseEntity receiveFormEntity(
            final @PathVariable("entityName") String entityName,
            final @RequestBody String formJson,
            final @RequestHeader Map<String, String> headers) {
        try
        {
            final RequestParameter parameter = new RequestParameter();
            parameter.setEntityCode(entityName);
            parameter.setToken(headers.get("authorization"));
            this.formFacade.registerForm(formJson, parameter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (Exception e)
        {
            LOG.log(Level.SEVERE, "The response was sent empty.");
            LOG.log(Level.SEVERE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(
            path = "/form/{entityName}/{code}",
            headers = "Content-Type=application/json",
            produces = "application/json")
    public ResponseEntity updateFormEntity(
            final @PathVariable(value = "entityName", required = false) String entityName,
            final @PathVariable(value = "code", required = false) String code,
            final @RequestBody(required = false) String formJson,
            final @RequestHeader Map<String, String> headers) {
        try
        {
            final RequestParameter parameter = new RequestParameter();
            parameter.setEntityCode(entityName);
            parameter.setToken(headers.get("authorization"));
            this.formFacade.registerForm(formJson, parameter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (Exception e)
        {
            LOG.log(Level.SEVERE, "The response was sent empty.");
            LOG.log(Level.SEVERE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(
            path = "/form/{entityName}/{code}",
            produces = "application/json")
    public ResponseEntity deleteFormEntity(
            final @PathVariable("entityName") String entityName,
            final @PathVariable("code") String code,
            final @RequestHeader Map<String, String> headers) {
        try
        {
            final RequestParameter parameter = new RequestParameter();
            parameter.setEntityCode(entityName);
            parameter.setCode(code);
            parameter.setToken(headers.get("authorization"));
            this.formFacade.deleteForm(parameter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (Exception e)
        {
            LOG.log(Level.SEVERE, "The response was sent empty.");
            LOG.log(Level.SEVERE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}