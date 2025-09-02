package com.example.layered.project.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class Project {
    private String id;
    private final String name;
    private final String supervisorId;
    private Long pay;
    private final Date createdAt;

    @Builder
    public Project(String name, Long pay, String supervisorId) {
        this.name = name;
        this.createdAt = new Date();
        this.supervisorId = supervisorId;

        if (this.pay != null && this.pay < 100000) {
            throw new IllegalStateException("pay is less than 100000");
        }

        this.pay = pay;
    }
}
