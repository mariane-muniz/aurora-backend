package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "entity_entries")
public class EntityEntryModel extends AbstractAudit {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private EntityModel entity;

    @Column(nullable = false)
    private String type;

    @ManyToMany(mappedBy = "entries")
    private Set<TableConfigModel> tableConfigs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private FarmModel farm;

    @ManyToMany(mappedBy = "entries")
    private Set<FormConfigModel> formConfigs;
}