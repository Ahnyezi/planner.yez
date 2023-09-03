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
@Table(name = "TB_MST_PLANS")
@Entity
@IdClass(PlanPK.class)
public class Plan {

    @Id
    @Column(name = "USER_ID")
    @NonNull
    String userId;
    @Id
    @Column(name = "DATE")
    @NonNull
    String date;
    @Column(name = "WEATHER")
    String weather;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DT")
    Date createDt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_DT")
    Date modifyDt;

    public void modifyWeather(String weather) {
        this.weather = weather;
    }
}
