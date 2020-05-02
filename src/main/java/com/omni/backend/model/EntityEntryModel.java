package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "entity_entries")
public class EntityEntryModel extends AbstractAudit {
    private static final long serialVersionUID = 1L;

    @Column(name = "entity_code", nullable = false, unique = true)
    private String entityCode;
    @Column(nullable = false)
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private FarmModel farm;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private EntityModel entity;
}