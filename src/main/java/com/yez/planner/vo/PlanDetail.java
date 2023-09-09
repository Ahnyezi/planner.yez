package com.yez.planner.vo;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@DynamicUpdate
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TB_PLN_DETAIL")
@Entity
@IdClass(PlanDetailPK.class)
public class PlanDetail {

    @Id
    @Column(name = "USER_ID", length = 20)
    @NonNull
    private String userId;
    @Id
    @Column(name = "DATE", length = 8)
    @NonNull
    private String date;
    @Id
    @Column(name = "TIME", length = 4)
    @NonNull
    private String time;
    @Column(name = "CHECK_YN", length = 1)
    @NonNull
    private String checkYn;
    @Column(name = "DESCRIPTION", length = 255)
    @NonNull
    private String description;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DT")
    private Date createDt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_DT")
    private Date modifyDt;
}
