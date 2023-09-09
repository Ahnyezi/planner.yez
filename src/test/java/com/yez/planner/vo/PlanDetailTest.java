package com.yez.planner.vo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlanDetailTest {

    String userId = "anyeji";
    String date = "20230909";
    String time = "0900";
    String checkYn = "N";
    String description = "필라테스 수업";


    @Test
    @Order(1)
    @DisplayName("Non-Nullable, Nullable 필드가 모두 입력된 경우")
    void successCreateTest() {
        PlanDetail planDetail = PlanDetail.builder()
                .userId(userId)
                .date(date)
                .time(time)
                .checkYn(checkYn)
                .description(description)
                .build();

        assertEquals(planDetail.getUserId(), userId);
        assertEquals(planDetail.getDate(), date);
        assertEquals(planDetail.getTime(), time);
        assertEquals(planDetail.getCheckYn(), checkYn);
        assertEquals(planDetail.getDescription(), description);
    }

    @Test
    @Order(2)
    @DisplayName("Non-Nullable 필드(time)가 누락된 경우")
    void failureCreateTest() {
        assertThrows(NullPointerException.class, () -> PlanDetail.builder()
                                                                    .userId(userId)
                                                                    .date(date)
                                                                    .checkYn(checkYn)
                                                                    .description(description)
                                                                    .build());
    }
}