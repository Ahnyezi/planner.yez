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
@Table(name = "TB_PLN_MASTER")
@Entity
@IdClass(PlanMasterPK.class)
public class PlanMaster {

    @Id
    @Column(name = "USER_ID", length = 20)
    @NonNull
    private String userId;
    @Id
    @Column(name = "DATE", length = 8)
    @NonNull
    private String date;
    @Column(name = "WEATHER", length = 20)
    private String weather;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DT")
    private Date createDt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_DT")
    private Date modifyDt;

    public void modifyWeather(String weather) {
        this.weather = weather;
    }
}
