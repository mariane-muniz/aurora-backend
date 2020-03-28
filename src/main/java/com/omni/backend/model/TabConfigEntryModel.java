package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tab_config_entries")
public class TabConfigEntryModel extends AbstractAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_config_id", nullable = false)
    private TabConfigModel config;


    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "tabConfigEntry"
    )
    private Set<TabConfigEntryComponentModel> components;
}