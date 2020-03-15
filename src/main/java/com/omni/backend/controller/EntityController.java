package com.omni.backend.controller;

import com.omni.backend.dto.TableData;
import com.omni.backend.facade.TableFacade;
import com.omni.backend.parameter.TableParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class EntityController {

    private static final Logger LOG = Logger.getLogger(EntityController.class.getName());
    private final TableFacade tableFacade;

    @GetMapping(
            path = "/entity/{entityName}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity getEntity(@PathVariable("entityName") String entityName) {
        try {
            final TableParameter parameter = new TableParameter();
            parameter.setEntityCode(entityName);
            final TableData data = this.tableFacade.getTable(parameter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
        }
        catch (Exception e) {
            LOG.log(Level.SEVERE, "The response was sent empty.");
            LOG.log(Level.SEVERE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}