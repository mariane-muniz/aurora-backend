package com.omni.backend.model;

import com.omni.aurora.core.model.AbstractAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "table_config_actions")
public class TableConfigActionModel extends AbstractAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column
    private String icon;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String btnType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "config_group_id", nullable = false)
    private TableGroupActionModel configGroup;
}
