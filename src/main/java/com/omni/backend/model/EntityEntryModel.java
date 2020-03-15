package com.omni.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "entity_entries")
public class EntityEntryModel extends AbstractAudit
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private EntityModel entity;

    @Column(nullable = false)
    private Types type;

    @Column(nullable = false)
    private String publicName;

    @ManyToMany(mappedBy = "entries")
    private Set<TableConfigModel> tableConfigs;
}