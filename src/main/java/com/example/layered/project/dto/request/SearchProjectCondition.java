package com.example.layered.project.dto.request;

import com.example.layered.project.domain.Project;

public record SearchProjectCondition(
        int id
        , String name
        , long minPay
        , long maxPay
        , String supervisorId) {
    public SearchProjectCondition {
        if (minPay > maxPay) {
            throw new IllegalArgumentException("minPay는 maxPay보다 클 수 없습니다.");
        }
    }
}
