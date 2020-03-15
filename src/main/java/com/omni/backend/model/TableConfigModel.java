package com.omni.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "table_configs")
public class TableConfigModel extends AbstractAudit {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "config_entry_rel",
            joinColumns = @JoinColumn(name = "config_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "entity_entry_id", referencedColumnName = "id")
    )
    private Set<EntityEntryModel> entries;

    @Column(nullable = false)
    private boolean displayPagination = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private EntityModel entity;
}