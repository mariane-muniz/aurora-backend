package com.omni.backend.controller;

import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.Types;
import com.omni.backend.repository.EntityEntryModelRepository;
import com.omni.backend.repository.EntityModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class BackendTestController
{
    private static final Logger LOG = Logger.getLogger(BackendTestController.class.getName());
    private final EntityEntryModelRepository entityEntryModelRepository;
    private final EntityModelRepository entityModelRepository;

    @ResponseBody
    @GetMapping("/test1")
    public String test()
    {
        if (!this.entityEntryModelRepository.findAll().iterator().hasNext()) {
            this.createEntity();
        }
        final EntityModel entity = this.entityModelRepository.findAll().iterator().next();

        EntityEntryModel entry = new EntityEntryModel();
        entry.setName("status");
        entry.setEntity(entity);
        entry.setDescription("Status do produto");
        entry.setPublicName("Status");
        entry.setType(Types.BOOLEAN);
        this.entityEntryModelRepository.save(entry);

        return "That's work!";
    }

    private void createEntity() {
        final EntityModel entity = new EntityModel();
        entity.setName("product");
        this.entityModelRepository.save(entity);
        LOG.log(Level.INFO, entity.getId().toString());
    }

    @GetMapping("/test2")
    @ResponseBody
    public String getTest() {








        return "ok";
    }
}