package com.yez.planner.vo;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class PlanPK implements Serializable {

    @Column(name = "USER_ID", nullable = false)
    private String userId;
    @Column(name = "DATE", nullable = false)
    private String date;

}
