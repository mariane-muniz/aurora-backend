package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tab_configs")
public class TabConfigModel extends AbstractAudit {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(name = "entity_code", nullable = false, unique = false)
    private String entityCode;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "config"
    )
    private Set<TabConfigEntryModel> entries;
}
