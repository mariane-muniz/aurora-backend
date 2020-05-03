package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "table_configs")
public class TableConfigModel extends AbstractAudit {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean displayPagination = false;
    @ElementCollection
    private List<String> entries;
    @Column(name = "entity_code", nullable = false, unique = false)
    private String entityCode;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "tableConfig"
    )
    private Set<TableGroupActionModel> actionGroups;
}