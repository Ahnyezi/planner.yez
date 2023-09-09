package com.yez.planner.vo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlanMemoTest {

    String userId = "anyeji";
    String date = "20230909";
    String content = "주중에 혼자 일하느라 넘 고생했다!\n주말엔 좀 여유롭게 보내기";

    @Test
    @Order(1)
    @DisplayName("Non-Nullable, Nullable 필드가 모두 입력된 경우")
    void successCreateTest() {
        PlanMemo planMemo = PlanMemo.builder()
                .userId(userId)
                .date(date)
                .content(content)
                .build();

        assertEquals(planMemo.getUserId(), userId);
        assertEquals(planMemo.getDate(), date);
        assertEquals(planMemo.getContent(), content);
    }

    @Test
    @Order(2)
    @DisplayName("Non-Nullable 필드(date)가 누락된 경우")
    void failureCreateTest() {
        assertThrows(NullPointerException.class, () -> PlanMemo.builder()
                                                                .userId(userId)
                                                                .content(content)
                                                                .build());
    }

}