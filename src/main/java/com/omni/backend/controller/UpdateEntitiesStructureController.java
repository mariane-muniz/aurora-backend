package com.omni.backend.controller;

import com.omni.backend.facade.EntityStructureFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@CrossOrigin("*")
@RequestMapping("/update-structure")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateEntitiesStructureController {
    private final EntityStructureFacade entityStructureFacade;

    @GetMapping
    public ResponseEntity<String> updateStructure(final @RequestHeader Map<String, String> headers) {
        final String token = headers.get("authorization");
        this.entityStructureFacade.updateStructure(token);
        return ResponseEntity.ok().build();
    }
}