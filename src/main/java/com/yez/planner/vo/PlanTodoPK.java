package com.yez.planner.vo;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class PlanTodoPK implements Serializable {

    @Column(name = "USER_ID", length = 20)
    @NonNull
    private String userId;
    @Column(name = "DATE", length = 8)
    @NonNull
    private String date;
    @Column(name = "SEQ")
    private int seq;

}
