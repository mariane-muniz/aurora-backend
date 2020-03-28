package com.omni.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
public class BackendTestController {

    private final EntityManager em;

    @GetMapping("/test")
    public void test() {
        this.em.getEntityManagerFactory();

//        this.em.getMetamodel().getEntities().iterator().next().getAttributes().iterator().next().
    }
}