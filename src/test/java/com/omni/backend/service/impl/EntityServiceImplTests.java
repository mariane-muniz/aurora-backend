package com.omni.backend.service.impl;

import com.omni.backend.model.EntityModel;
import com.omni.backend.service.EntityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class EntityServiceImplTests {
    @Mock
    private EntityService entityService;

    @Test
    public void findEntityTest() {
        EntityModel e = new EntityModel();
        e.setCode("product");
        verify(this.entityService.findEntity("product")).isPresent();
    }
}
