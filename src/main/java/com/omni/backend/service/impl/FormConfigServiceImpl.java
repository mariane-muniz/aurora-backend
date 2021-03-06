package com.omni.backend.service.impl;

import com.omni.backend.model.FormConfigModel;
import com.omni.backend.repository.FormConfigRepository;
import com.omni.backend.service.FormConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FormConfigServiceImpl implements FormConfigService {
    private final FormConfigRepository formConfigRepository;

    @Override
    public FormConfigModel getForm(final String entityCode) {
        Optional<FormConfigModel> form = this.formConfigRepository.findOneByEntityCode(entityCode);
        return form.orElseGet(() -> this.formConfigRepository.findOneByEntityCode("general").orElse(null));
    }
}