package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "entity_actions")
@Entity
@Getter
@Setter
public class EntityActionModel extends AbstractAudit {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String text;
    @Column(name = "entity_code", nullable = false, unique = true)
    private String entityCode;
    private boolean multiple;
    private boolean existent;
    private String rules;

}