package com.omni.backend.controller;

import com.omni.backend.dto.FormData;
import com.omni.backend.facade.FormFacade;
import com.omni.backend.parameter.RequestParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@CrossOrigin(origins = "*")
public class EntityFormController {

    private static final Logger LOG = Logger.getLogger(EntityTableController.class.getName());
    private final FormFacade formFacade;

    @GetMapping(
            path = "/form/{entityName}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity getFormEntity(
            final @PathVariable("entityName") String entityName,
            final @RequestHeader Map<String, String> headers) {
        try {
            final RequestParameter parameter = new RequestParameter();
            parameter.setEntityCode(entityName);
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

    @PostMapping(
            path = "/form/{entityName}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity receiveFormEntity(
            final @PathVariable("entityName") String entityName,
            final @RequestHeader Map<String, String> headers) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("null");
        }
        catch (Exception e) {
            LOG.log(Level.SEVERE, "The response was sent empty.");
            LOG.log(Level.SEVERE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}