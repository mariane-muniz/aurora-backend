package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "entities")
@javax.persistence.Entity
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EntityModel extends AbstractAudit {
    private static final long serialVersionUID = 1L;

    public EntityModel() {
        this.attributes = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

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
    private Set<TableConfigModel> configs;
}