package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "form_configs")
public class FormConfigModel extends AbstractAudit {
    private static final long serialVersionUID = 1L;

    @Column(name = "entity_code", nullable = false, unique = false)
    private String entityCode;
    @ElementCollection
    private List<String> entries;
}