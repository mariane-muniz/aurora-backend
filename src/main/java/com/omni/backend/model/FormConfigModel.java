package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "form_configs")
public class FormConfigModel extends AbstractAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private EntityModel entity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "form_entry_rel",
            joinColumns = @JoinColumn(name = "config_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "entity_entry_id", referencedColumnName = "id")
    )
    private Set<EntityEntryModel> entries;
}
