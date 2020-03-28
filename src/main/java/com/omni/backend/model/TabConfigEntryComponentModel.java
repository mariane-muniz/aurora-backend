package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tab_config_entry_components")
public class TabConfigEntryComponentModel extends AbstractAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private ComponentTypes type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_config_entry_id", nullable = false)
    private TabConfigEntryModel tabConfigEntry;
}
