package com.omni.backend.controller;

import com.omni.backend.dto.TableActionGroupData;
import com.omni.backend.dto.TableData;
import com.omni.backend.facade.TableFacade;
import com.omni.backend.parameter.RequestParameter;
import com.omni.backend.parameter.TableParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@CrossOrigin(origins = "*")
public class EntityTableController {

    private static final Logger LOG = Logger.getLogger(EntityTableController.class.getName());
    private final TableFacade tableFacade;

    @GetMapping(
            path = "/table/{entityName}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity getTable(final @PathVariable("entityName") String entityName,
                                   final @RequestHeader Map<String, String> headers) {
        try {
            final TableParameter parameter = new TableParameter();
            parameter.setEntityCode(entityName);
            parameter.setToken(headers.get("authorization"));
            final TableData data = this.tableFacade.getTable(parameter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
        }
        catch (Exception e) {
            LOG.log(Level.SEVERE, "The response was sent empty.");
            LOG.log(Level.SEVERE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/table-actions/{entityName}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity getTableActions(final @PathVariable("entityName") String entityName,
                                          final @RequestHeader Map<String, String> headers) {
        try {
            final RequestParameter parameter = new RequestParameter();
            parameter.setEntityCode(entityName);
            parameter.setToken(headers.get("authorization"));
            final Set<TableActionGroupData> data = this.tableFacade.getTableActions(parameter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
        }
        catch (Exception e) {
            LOG.log(Level.SEVERE, "The response was sent empty.");
            LOG.log(Level.SEVERE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}