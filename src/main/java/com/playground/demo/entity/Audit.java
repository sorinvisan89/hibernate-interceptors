package com.playground.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @Column(name = "id", nullable = false)
    private Integer auditId;

    @Column(name = "action_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    public enum ActionType{

        INSERT,

        UPDATE,

        DELETE
    }
}
