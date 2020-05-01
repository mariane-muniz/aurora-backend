package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Table(name = "entities")
@javax.persistence.Entity
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EntityModel extends AbstractAudit {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long version;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "entity"
    )
    private Set<EntityEntryModel> attributes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private FarmModel farm;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "entity"
    )
    private Set<TableConfigModel> tableConfigs;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "entity"
    )
    private Set<TabConfigModel> TabConfigs;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "entity"
    )
    private Set<FormConfigModel> formConfigs;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "entity"
    )
    private Set<FormConfigModel> actions;
}