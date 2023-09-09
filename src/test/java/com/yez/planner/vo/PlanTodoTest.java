package com.yez.planner.vo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlanTodoTest {

    String userId = "anyeji";
    String date = "20220909";
    int seq = 1;
    String content = "푸딩 사러가기";

    @Test
    @Order(1)
    @DisplayName("Non-Nullable, Nullable 필드가 모두 입력된 경우")
    void successCreateTest() {
        PlanTodo planTodo = PlanTodo.builder()
                .userId(userId)
                .date(date)
                .seq(seq)
                .content(content)
                .build();

        assertEquals(planTodo.getUserId(), userId);
        assertEquals(planTodo.getDate(), date);
        assertEquals(planTodo.getContent(), content);
    }

    @Test
    @Order(2)
    @DisplayName("Non-Nullable 필드(date)가 누락된 경우")
    void failureCreateTest() {
        assertThrows(NullPointerException.class, () -> PlanTodo.builder()
                                                                .userId(userId)
                                                                .seq(seq)
                                                                .content(content)
                                                                .build());
    }


}