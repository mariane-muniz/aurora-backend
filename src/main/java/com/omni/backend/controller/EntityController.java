package com.omni.backend.controller;

import com.omni.backend.dto.Data;
import com.omni.backend.dto.TableData;
import com.omni.backend.facade.TableFacade;
import com.omni.backend.parameter.TableParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class EntityController {

    private static final Logger LOG = Logger.getLogger(EntityController.class.getName());
    private final TableFacade tableFacade;

    @RequestMapping(
            path = "/entity/{entityName}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Data> getEntity(@PathVariable("entityName") String entityName) {
        try {
            final TableParameter parameter = new TableParameter();
            parameter.setEntityName(entityName);
            final TableData data = this.tableFacade.getTable(parameter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
        }
        catch (Exception e) {
            LOG.log(Level.SEVERE, "Fail on request", e);
        }
        return ResponseEntity.badRequest().build();
    }
}