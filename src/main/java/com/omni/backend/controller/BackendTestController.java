package com.omni.backend.controller;

import com.omni.backend.model.TableConfigModel;
import com.omni.backend.repository.TableConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
public class BackendTestController {

    private final TableConfigRepository tableConfigRepository;

    @GetMapping("/test")
    public void test() {

        List<String> list = new ArrayList<>();
        list.add("dadas");
        list.add("asd222");
        TableConfigModel e = new TableConfigModel();
        e.setEntityCode("asdsa");
        e.setCode("casdsa");
        e.setEntries(list);

        this.tableConfigRepository.save(e);
    }
}